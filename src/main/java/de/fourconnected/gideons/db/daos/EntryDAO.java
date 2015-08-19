package de.fourconnected.gideons.db.daos;

import de.fourconnected.gideons.db.entities.Entry;

import java.util.List;

/**
 * Created by david on 19.08.15.
 */
public interface EntryDAO {
    Entry persist(Entry entry);
    void remove(Entry entry);
    Entry getById(long id);
    List<Entry> getByIds(Long[] ids);
    List<Entry> getSorted(String tenant, String sortColumn, boolean descending, int offset, int limit);
}
