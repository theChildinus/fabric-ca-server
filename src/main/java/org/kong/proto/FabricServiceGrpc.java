package org.kong.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.17.1)",
    comments = "Source: fabric_service.proto")
public final class FabricServiceGrpc {

  private FabricServiceGrpc() {}

  public static final String SERVICE_NAME = "proto.FabricService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.kong.proto.RegisterReq,
      org.kong.proto.RegisterResp> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Register",
      requestType = org.kong.proto.RegisterReq.class,
      responseType = org.kong.proto.RegisterResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.kong.proto.RegisterReq,
      org.kong.proto.RegisterResp> getRegisterMethod() {
    io.grpc.MethodDescriptor<org.kong.proto.RegisterReq, org.kong.proto.RegisterResp> getRegisterMethod;
    if ((getRegisterMethod = FabricServiceGrpc.getRegisterMethod) == null) {
      synchronized (FabricServiceGrpc.class) {
        if ((getRegisterMethod = FabricServiceGrpc.getRegisterMethod) == null) {
          FabricServiceGrpc.getRegisterMethod = getRegisterMethod = 
              io.grpc.MethodDescriptor.<org.kong.proto.RegisterReq, org.kong.proto.RegisterResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "proto.FabricService", "Register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.kong.proto.RegisterReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.kong.proto.RegisterResp.getDefaultInstance()))
                  .setSchemaDescriptor(new FabricServiceMethodDescriptorSupplier("Register"))
                  .build();
          }
        }
     }
     return getRegisterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.kong.proto.DownloadReq,
      org.kong.proto.DownloadResp> getDownloadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Download",
      requestType = org.kong.proto.DownloadReq.class,
      responseType = org.kong.proto.DownloadResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.kong.proto.DownloadReq,
      org.kong.proto.DownloadResp> getDownloadMethod() {
    io.grpc.MethodDescriptor<org.kong.proto.DownloadReq, org.kong.proto.DownloadResp> getDownloadMethod;
    if ((getDownloadMethod = FabricServiceGrpc.getDownloadMethod) == null) {
      synchronized (FabricServiceGrpc.class) {
        if ((getDownloadMethod = FabricServiceGrpc.getDownloadMethod) == null) {
          FabricServiceGrpc.getDownloadMethod = getDownloadMethod = 
              io.grpc.MethodDescriptor.<org.kong.proto.DownloadReq, org.kong.proto.DownloadResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "proto.FabricService", "Download"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.kong.proto.DownloadReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.kong.proto.DownloadResp.getDefaultInstance()))
                  .setSchemaDescriptor(new FabricServiceMethodDescriptorSupplier("Download"))
                  .build();
          }
        }
     }
     return getDownloadMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FabricServiceStub newStub(io.grpc.Channel channel) {
    return new FabricServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FabricServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FabricServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FabricServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FabricServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class FabricServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void register(org.kong.proto.RegisterReq request,
        io.grpc.stub.StreamObserver<org.kong.proto.RegisterResp> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    /**
     */
    public void download(org.kong.proto.DownloadReq request,
        io.grpc.stub.StreamObserver<org.kong.proto.DownloadResp> responseObserver) {
      asyncUnimplementedUnaryCall(getDownloadMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.kong.proto.RegisterReq,
                org.kong.proto.RegisterResp>(
                  this, METHODID_REGISTER)))
          .addMethod(
            getDownloadMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.kong.proto.DownloadReq,
                org.kong.proto.DownloadResp>(
                  this, METHODID_DOWNLOAD)))
          .build();
    }
  }

  /**
   */
  public static final class FabricServiceStub extends io.grpc.stub.AbstractStub<FabricServiceStub> {
    private FabricServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FabricServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FabricServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FabricServiceStub(channel, callOptions);
    }

    /**
     */
    public void register(org.kong.proto.RegisterReq request,
        io.grpc.stub.StreamObserver<org.kong.proto.RegisterResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void download(org.kong.proto.DownloadReq request,
        io.grpc.stub.StreamObserver<org.kong.proto.DownloadResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDownloadMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FabricServiceBlockingStub extends io.grpc.stub.AbstractStub<FabricServiceBlockingStub> {
    private FabricServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FabricServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FabricServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FabricServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.kong.proto.RegisterResp register(org.kong.proto.RegisterReq request) {
      return blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.kong.proto.DownloadResp download(org.kong.proto.DownloadReq request) {
      return blockingUnaryCall(
          getChannel(), getDownloadMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FabricServiceFutureStub extends io.grpc.stub.AbstractStub<FabricServiceFutureStub> {
    private FabricServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FabricServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FabricServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FabricServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.kong.proto.RegisterResp> register(
        org.kong.proto.RegisterReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.kong.proto.DownloadResp> download(
        org.kong.proto.DownloadReq request) {
      return futureUnaryCall(
          getChannel().newCall(getDownloadMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER = 0;
  private static final int METHODID_DOWNLOAD = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FabricServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FabricServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((org.kong.proto.RegisterReq) request,
              (io.grpc.stub.StreamObserver<org.kong.proto.RegisterResp>) responseObserver);
          break;
        case METHODID_DOWNLOAD:
          serviceImpl.download((org.kong.proto.DownloadReq) request,
              (io.grpc.stub.StreamObserver<org.kong.proto.DownloadResp>) responseObserver);
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

  private static abstract class FabricServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FabricServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.kong.proto.RegisterProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FabricService");
    }
  }

  private static final class FabricServiceFileDescriptorSupplier
      extends FabricServiceBaseDescriptorSupplier {
    FabricServiceFileDescriptorSupplier() {}
  }

  private static final class FabricServiceMethodDescriptorSupplier
      extends FabricServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FabricServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (FabricServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FabricServiceFileDescriptorSupplier())
              .addMethod(getRegisterMethod())
              .addMethod(getDownloadMethod())
              .build();
        }
      }
    }
    return result;
  }
}
