package com.example.systemevents.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: SystemEvents.proto")
public final class SystemEventsServiceGrpc {

  private SystemEventsServiceGrpc() {}

  public static final String SERVICE_NAME = "SystemEventsService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.systemevents.grpc.SystemEventsRequest,
      com.example.systemevents.grpc.SystemEventsResponse> getGetActionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAction",
      requestType = com.example.systemevents.grpc.SystemEventsRequest.class,
      responseType = com.example.systemevents.grpc.SystemEventsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.systemevents.grpc.SystemEventsRequest,
      com.example.systemevents.grpc.SystemEventsResponse> getGetActionMethod() {
    io.grpc.MethodDescriptor<com.example.systemevents.grpc.SystemEventsRequest, com.example.systemevents.grpc.SystemEventsResponse> getGetActionMethod;
    if ((getGetActionMethod = SystemEventsServiceGrpc.getGetActionMethod) == null) {
      synchronized (SystemEventsServiceGrpc.class) {
        if ((getGetActionMethod = SystemEventsServiceGrpc.getGetActionMethod) == null) {
          SystemEventsServiceGrpc.getGetActionMethod = getGetActionMethod = 
              io.grpc.MethodDescriptor.<com.example.systemevents.grpc.SystemEventsRequest, com.example.systemevents.grpc.SystemEventsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "SystemEventsService", "getAction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.systemevents.grpc.SystemEventsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.systemevents.grpc.SystemEventsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SystemEventsServiceMethodDescriptorSupplier("getAction"))
                  .build();
          }
        }
     }
     return getGetActionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SystemEventsServiceStub newStub(io.grpc.Channel channel) {
    return new SystemEventsServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SystemEventsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SystemEventsServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SystemEventsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SystemEventsServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class SystemEventsServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getAction(com.example.systemevents.grpc.SystemEventsRequest request,
                          io.grpc.stub.StreamObserver<com.example.systemevents.grpc.SystemEventsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetActionMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetActionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.systemevents.grpc.SystemEventsRequest,
                com.example.systemevents.grpc.SystemEventsResponse>(
                  this, METHODID_GET_ACTION)))
          .build();
    }
  }

  /**
   */
  public static final class SystemEventsServiceStub extends io.grpc.stub.AbstractStub<SystemEventsServiceStub> {
    private SystemEventsServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SystemEventsServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SystemEventsServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SystemEventsServiceStub(channel, callOptions);
    }

    /**
     */
    public void getAction(com.example.systemevents.grpc.SystemEventsRequest request,
                          io.grpc.stub.StreamObserver<com.example.systemevents.grpc.SystemEventsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetActionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SystemEventsServiceBlockingStub extends io.grpc.stub.AbstractStub<SystemEventsServiceBlockingStub> {
    private SystemEventsServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SystemEventsServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SystemEventsServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SystemEventsServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.systemevents.grpc.SystemEventsResponse getAction(com.example.systemevents.grpc.SystemEventsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetActionMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SystemEventsServiceFutureStub extends io.grpc.stub.AbstractStub<SystemEventsServiceFutureStub> {
    private SystemEventsServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SystemEventsServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SystemEventsServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SystemEventsServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.systemevents.grpc.SystemEventsResponse> getAction(
        com.example.systemevents.grpc.SystemEventsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetActionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ACTION = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SystemEventsServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SystemEventsServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ACTION:
          serviceImpl.getAction((com.example.systemevents.grpc.SystemEventsRequest) request,
              (io.grpc.stub.StreamObserver<com.example.systemevents.grpc.SystemEventsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SystemEventsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SystemEventsServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return SystemEvents.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SystemEventsService");
    }
  }

  private static final class SystemEventsServiceFileDescriptorSupplier
      extends SystemEventsServiceBaseDescriptorSupplier {
    SystemEventsServiceFileDescriptorSupplier() {}
  }

  private static final class SystemEventsServiceMethodDescriptorSupplier
      extends SystemEventsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SystemEventsServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SystemEventsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SystemEventsServiceFileDescriptorSupplier())
              .addMethod(getGetActionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
