package com.smartisanos.smartfolder.aoa.p056h;

import java.io.File;
import java.lang.reflect.Field;

/* renamed from: com.smartisanos.smartfolder.aoa.h.ae */
/* loaded from: classes.dex */
public final class StorageVolumeWrap {

    /* renamed from: a */
    public boolean removable;

    /* renamed from: b */
    private File path;

    /* renamed from: c */
    private String state;

    /* renamed from: d */
    private String fsUUID;

    /* renamed from: e */
    private String uuid;

    /* renamed from: f */
    private String id;

    /* renamed from: g */
    private boolean primary;

    public final String toString() {
        return "StorageVolumeWrap: path = " + this.path + ", Uuid = " + getUUID() + ", mId = "
                + this.id + ", state " + this.state + ", primary " + this.primary + ", removable " + this.removable;
    }

    public StorageVolumeWrap(Object obj) {
        Field[] declaredFields;
        try {
            for (Field field : Class.forName("android.os.storage.StorageVolume").getDeclaredFields()) {
                String name = field.getName();
                boolean isAccessible = field.isAccessible();
                if (!isAccessible) {
                    field.setAccessible(true);
                }
                Object obj2 = field.get(obj);
                if (!isAccessible) {
                    field.setAccessible(false);
                }
                if ("mPath".equals(name)) {
                    this.path = (File) obj2;
                } else if ("mState".equals(name)) {
                    this.state = (String) obj2;
                } else if ("mFsUuid".equals(name)) {
                    this.fsUUID = (String) obj2;
                } else if ("mUuid".equals(name)) {
                    this.uuid = (String) obj2;
                } else if ("mId".equals(name)) {
                    this.id = (String) obj2;
                } else if ("mPrimary".equals(name)) {
                    this.primary = ((Boolean) obj2).booleanValue();
                } else if ("mRemovable".equals(name)) {
                    this.removable = ((Boolean) obj2).booleanValue();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: a */
    public final boolean isPrimary() {
        return this.primary;
    }

    /* renamed from: b */
    public final String getUUID() {
        return this.fsUUID != null ? this.fsUUID : this.uuid;
    }

    /* renamed from: c */
    public final File getPath() {
        return this.path;
    }

    /* renamed from: d */
    public final boolean isMounted() {
        return "mounted".equals(this.state) || "mounted_ro".equals(this.state);
    }
}
