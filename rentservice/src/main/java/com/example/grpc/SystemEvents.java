// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SystemEvents.proto

package com.example.grpc;

public final class SystemEvents {
  private SystemEvents() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_SystemEventsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_SystemEventsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_SystemEventsResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_SystemEventsResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022SystemEvents.proto\"r\n\023SystemEventsRequ" +
      "est\022\021\n\ttimeStamp\030\001 \001(\t\022\024\n\014microservice\030\002" +
      " \001(\t\022\016\n\006action\030\003 \001(\t\022\020\n\010resource\030\004 \001(\t\022\020" +
      "\n\010response\030\005 \001(\t\"/\n\024SystemEventsResponse" +
      "\022\027\n\017responseMessage\030\001 \001(\t2O\n\023SystemEvent" +
      "sService\0228\n\tgetAction\022\024.SystemEventsRequ" +
      "est\032\025.SystemEventsResponseB\024\n\020com.exampl" +
      "e.grpcP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_SystemEventsRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_SystemEventsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_SystemEventsRequest_descriptor,
        new java.lang.String[] { "TimeStamp", "Microservice", "Action", "Resource", "Response", });
    internal_static_SystemEventsResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_SystemEventsResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_SystemEventsResponse_descriptor,
        new java.lang.String[] { "ResponseMessage", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
