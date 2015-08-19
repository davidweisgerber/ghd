package de.fourconnected.gideons.db.daos;

import de.fourconnected.gideons.db.entities.Entry;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

/**
 * Created by david on 19.08.15.
 */
@Repository
public class EntryJPADAO implements EntryDAO {
    @PersistenceContext
    EntityManager em;

    @Override
    public Entry persist(Entry entry) {
        return this.em.merge(entry);
    }

    @Override
    public void remove(Entry entry) {
        this.em.remove(entry);
    }

    @Override
    public Entry getById(long id) {
        return this.em.find(Entry.class, id);
    }

    @Override
    public List<Entry> getByIds(Long[] ids) {
        TypedQuery<Entry> query = this.em.createQuery("from Entry as e where e.id in :ids", Entry.class);
        query.setParameter("ids", Arrays.asList(ids));

        return query.getResultList();
    }

    @Override
    public List<Entry> getSorted(String tenant, String sortColumn, boolean descending, int offset, int limit) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<Entry> c = cb.createQuery(Entry.class);
        Root<Entry> entries = c.from(Entry.class);
        c.where(cb.equal(entries.get("tenant"), cb.parameter(String.class, "tenant")));
        c.orderBy(cb.desc(entries.get(sortColumn)));

        TypedQuery<Entry> query = this.em.createQuery(c);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setParameter("tenant", tenant);

        return query.getResultList();
    }
}
