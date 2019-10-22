// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fabric_service.proto

package org.kong.proto;

public final class FabricServiceProto {
  private FabricServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_RegisterReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_RegisterReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_RegisterResp_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_RegisterResp_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_DownloadReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_DownloadReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_DownloadResp_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_DownloadResp_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_LoginReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_LoginReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_LoginResp_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_proto_LoginResp_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024fabric_service.proto\022\005proto\"/\n\013Registe" +
      "rReq\022\016\n\006userid\030\001 \001(\003\022\020\n\010username\030\002 \001(\t\"\034" +
      "\n\014RegisterResp\022\014\n\004code\030\001 \001(\003\"/\n\013Download" +
      "Req\022\016\n\006userid\030\001 \001(\003\022\020\n\010username\030\002 \001(\t\"\034\n" +
      "\014DownloadResp\022\014\n\004card\030\001 \001(\t\">\n\010LoginReq\022" +
      "\016\n\006userid\030\001 \001(\003\022\020\n\010username\030\002 \001(\t\022\020\n\010use" +
      "rhash\030\003 \001(\t\"\031\n\tLoginResp\022\014\n\004code\030\001 \001(\0032\253" +
      "\001\n\rFabricService\0225\n\010Register\022\022.proto.Reg" +
      "isterReq\032\023.proto.RegisterResp\"\000\0225\n\010Downl" +
      "oad\022\022.proto.DownloadReq\032\023.proto.Download" +
      "Resp\"\000\022,\n\005Login\022\017.proto.LoginReq\032\020.proto" +
      ".LoginResp\"\000B&\n\016org.kong.protoB\022FabricSe" +
      "rviceProtoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_proto_RegisterReq_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_RegisterReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_RegisterReq_descriptor,
        new java.lang.String[] { "Userid", "Username", });
    internal_static_proto_RegisterResp_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_proto_RegisterResp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_RegisterResp_descriptor,
        new java.lang.String[] { "Code", });
    internal_static_proto_DownloadReq_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_proto_DownloadReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_DownloadReq_descriptor,
        new java.lang.String[] { "Userid", "Username", });
    internal_static_proto_DownloadResp_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_proto_DownloadResp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_DownloadResp_descriptor,
        new java.lang.String[] { "Card", });
    internal_static_proto_LoginReq_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_proto_LoginReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_LoginReq_descriptor,
        new java.lang.String[] { "Userid", "Username", "Userhash", });
    internal_static_proto_LoginResp_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_proto_LoginResp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_LoginResp_descriptor,
        new java.lang.String[] { "Code", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}