// automatically generated by the FlatBuffers compiler, do not modify

package ru.nsu.fit.dib.projectdib.flatbuffersclasses.update;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class EntityStateData extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_2_0_8(); }
  public static EntityStateData getRootAsEntityStateData(ByteBuffer _bb) { return getRootAsEntityStateData(_bb, new EntityStateData()); }
  public static EntityStateData getRootAsEntityStateData(ByteBuffer _bb, EntityStateData obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public EntityStateData __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public int id() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public ru.nsu.fit.dib.projectdib.flatbuffersclasses.update.Point2D pos() { return pos(new ru.nsu.fit.dib.projectdib.flatbuffersclasses.update.Point2D()); }
  public ru.nsu.fit.dib.projectdib.flatbuffersclasses.update.Point2D pos(ru.nsu.fit.dib.projectdib.flatbuffersclasses.update.Point2D obj) { int o = __offset(6); return o != 0 ? obj.__assign(o + bb_pos, bb) : null; }
  public ru.nsu.fit.dib.projectdib.flatbuffersclasses.update.Point2D rot() { return rot(new ru.nsu.fit.dib.projectdib.flatbuffersclasses.update.Point2D()); }
  public ru.nsu.fit.dib.projectdib.flatbuffersclasses.update.Point2D rot(ru.nsu.fit.dib.projectdib.flatbuffersclasses.update.Point2D obj) { int o = __offset(8); return o != 0 ? obj.__assign(o + bb_pos, bb) : null; }
  public int activeWeapon() { int o = __offset(10); return o != 0 ? bb.getInt(o + bb_pos) : 0; }

  public static void startEntityStateData(FlatBufferBuilder builder) { builder.startTable(4); }
  public static void addId(FlatBufferBuilder builder, int id) { builder.addInt(0, id, 0); }
  public static void addPos(FlatBufferBuilder builder, int posOffset) { builder.addStruct(1, posOffset, 0); }
  public static void addRot(FlatBufferBuilder builder, int rotOffset) { builder.addStruct(2, rotOffset, 0); }
  public static void addActiveWeapon(FlatBufferBuilder builder, int activeWeapon) { builder.addInt(3, activeWeapon, 0); }
  public static int endEntityStateData(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public EntityStateData get(int j) { return get(new EntityStateData(), j); }
    public EntityStateData get(EntityStateData obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

