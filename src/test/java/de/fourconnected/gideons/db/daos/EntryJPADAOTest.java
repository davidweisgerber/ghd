package de.fourconnected.gideons.db.daos;

import de.fourconnected.gideons.db.entities.Entry;
import de.fourconnected.gideons.db.enums.EntryType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by david on 19.08.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test.xml")
public class EntryJPADAOTest {

    @Autowired
    EntryDAO entryDAO;

    public static Entry getEntry() {
        Entry entry = new Entry();
        entry.setBeds(10);
        entry.setRooms(5);
        entry.setCode("80933");
        entry.setComeBack(false);
        entry.setComment("Komischer Laden");
        entry.setDeclined(true);
        entry.setEntryType(EntryType.HOTEL);
        entry.setLastVisit(new Date());
        entry.setName("Hotel in München");
        entry.setNumberOfNT3(10);
        entry.setStreet("Landauer Straße 10");
        entry.setTown("München");
        entry.setTenant("München-West");

        return entry;
    }

    @Test
    @Transactional
    public void testPersist() throws Exception {
        Entry entry = this.entryDAO.persist(getEntry());
        assertTrue(entry.getId() > 0);
    }

    @Test
    @Transactional
    public void testRemove() throws Exception {
        Entry entry = this.entryDAO.persist(getEntry());
        assertTrue(entry.getId() > 0);

        this.entryDAO.remove(entry);
        assertTrue(this.entryDAO.getById(entry.getId()) == null);
    }

    @Test
    @Transactional
    public void testGetById() throws Exception {
        Entry entry = this.entryDAO.persist(getEntry());
        assertTrue(entry.getId() > 0);
        assertTrue(this.entryDAO.getById(entry.getId()) != null);
    }

    @Test
    @Transactional
    public void testGetByIds() throws Exception {
        Entry entry1 = this.entryDAO.persist(getEntry());
        Entry entry2 = this.entryDAO.persist(getEntry());
        Entry entry3 = this.entryDAO.persist(getEntry());

        List<Entry> entries = this.entryDAO.getByIds(new Long[]{entry1.getId(), entry2.getId()});
        assertTrue(entries.size() == 2);
        assertTrue(entries.get(0).getId() == entry1.getId());
        assertTrue(entries.get(1).getId() == entry2.getId());
    }

    @Test
    @Transactional
    public void testGetSorted() throws Exception {
        Entry entry1 = this.entryDAO.persist(getEntry());
        Entry entry2 = this.entryDAO.persist(getEntry());
        Entry entry3 = this.entryDAO.persist(getEntry());

        List<Entry> entries = this.entryDAO.getSorted(entry1.getTenant(), "id", true, 0, 100);
        assertTrue(entries.size() == 3);
        assertTrue(entries.get(0).getId() == entry3.getId());
        assertTrue(entries.get(1).getId() == entry2.getId());

        entries = this.entryDAO.getSorted(entry1.getTenant(), "code", true, 0, 2);
        assertTrue(entries.size() == 2);
    }
}
