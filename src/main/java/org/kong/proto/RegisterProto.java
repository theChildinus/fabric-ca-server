// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: register.proto

package org.kong.proto;

public final class RegisterProto {
  private RegisterProto() {}
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

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\016register.proto\022\005proto\"\037\n\013RegisterReq\022\020" +
      "\n\010username\030\001 \001(\t\"\034\n\014RegisterResp\022\014\n\004code" +
      "\030\001 \001(\0032A\n\010Register\0225\n\010Register\022\022.proto.R" +
      "egisterReq\032\023.proto.RegisterResp\"\000B!\n\016org" +
      ".kong.protoB\rRegisterProtoP\001b\006proto3"
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
        new String[] { "Username", });
    internal_static_proto_RegisterResp_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_proto_RegisterResp_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_proto_RegisterResp_descriptor,
        new String[] { "Code", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
