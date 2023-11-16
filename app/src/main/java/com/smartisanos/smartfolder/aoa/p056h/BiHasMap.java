package com.smartisanos.smartfolder.aoa.p056h;

import java.util.HashMap;

/* renamed from: com.smartisanos.smartfolder.aoa.h.b */
/* loaded from: classes.dex */
public final class BiHasMap<K, V> extends HashMap<K, V> {

    /* renamed from: a */
    private HashMap<V, K> f3671a;

    public BiHasMap() {
        this.f3671a = new HashMap<>();
    }

    public BiHasMap(byte b) {
        super(10);
        this.f3671a = new HashMap<>();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        if (super.containsKey(k)) {
            throw new IllegalArgumentException(k + " is already exist.");
        }
        if (this.f3671a.containsKey(v)) {
            throw new IllegalArgumentException(v + " is already exist.");
        }
        this.f3671a.put(v, k);
        return (V) super.put(k, v);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        V v = (V) super.remove(obj);
        this.f3671a.remove(v);
        return v;
    }

    /* renamed from: a */
    public final K m522a(V v) {
        return this.f3671a.get(v);
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        return super.toString() + this.f3671a.toString();
    }
}
