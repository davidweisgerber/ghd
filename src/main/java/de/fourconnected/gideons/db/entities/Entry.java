package de.fourconnected.gideons.db.entities;

import de.fourconnected.gideons.db.enums.EntryType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by david on 19.08.15.
 */
@Entity
@Table(indexes = @Index(columnList = "tenant"))
public class Entry {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(updatable = false)
    private Date creationDate;

    private Date modifiedDate;

    @Type(type="uuid-char")
    @Column(unique = true, updatable = false)
    private UUID guid;

    @Version
    private long version;

    String tenant;

    @Enumerated(EnumType.STRING)
    private EntryType entryType;

    private String name;

    private int beds;
    private int rooms;

    private String code;

    private String town;

    private String street;

    private Date lastVisit;

    private int numberOfNT3;

    private boolean declined;

    private boolean comeBack;

    private String comment;

    @Transient
    private static final UUID nullUUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @PrePersist
    public void prepersist() {
        if (this.guid == null || this.guid.equals(this.nullUUID)) {
            this.guid = UUID.randomUUID();
        }

        if (this.creationDate == null) {
            this.creationDate = new Date();
        }

        this.modifiedDate = new Date();
    }

    @PreUpdate
    public void preupdate() {
        this.modifiedDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public void setEntryType(EntryType entryType) {
        this.entryType = entryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public int getNumberOfNT3() {
        return numberOfNT3;
    }

    public void setNumberOfNT3(int numberOfNT3) {
        this.numberOfNT3 = numberOfNT3;
    }

    public boolean isDeclined() {
        return declined;
    }

    public void setDeclined(boolean declined) {
        this.declined = declined;
    }

    public boolean isComeBack() {
        return comeBack;
    }

    public void setComeBack(boolean comeBack) {
        this.comeBack = comeBack;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static UUID getNullUUID() {
        return nullUUID;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
}
