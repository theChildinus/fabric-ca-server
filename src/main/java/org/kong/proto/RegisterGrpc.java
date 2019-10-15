package org.kong.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.17.1)",
    comments = "Source: register.proto")
public final class RegisterGrpc {

  private RegisterGrpc() {}

  public static final String SERVICE_NAME = "proto.Register";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<RegisterReq,
      RegisterResp> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Register",
      requestType = RegisterReq.class,
      responseType = RegisterResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<RegisterReq,
      RegisterResp> getRegisterMethod() {
    io.grpc.MethodDescriptor<RegisterReq, RegisterResp> getRegisterMethod;
    if ((getRegisterMethod = RegisterGrpc.getRegisterMethod) == null) {
      synchronized (RegisterGrpc.class) {
        if ((getRegisterMethod = RegisterGrpc.getRegisterMethod) == null) {
          RegisterGrpc.getRegisterMethod = getRegisterMethod =
              io.grpc.MethodDescriptor.<RegisterReq, RegisterResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "proto.Register", "Register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  RegisterReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  RegisterResp.getDefaultInstance()))
                  .setSchemaDescriptor(new RegisterMethodDescriptorSupplier("Register"))
                  .build();
          }
        }
     }
     return getRegisterMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RegisterStub newStub(io.grpc.Channel channel) {
    return new RegisterStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RegisterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RegisterBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RegisterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RegisterFutureStub(channel);
  }

  /**
   */
  public static abstract class RegisterImplBase implements io.grpc.BindableService {

    /**
     */
    public void register(RegisterReq request,
                         io.grpc.stub.StreamObserver<RegisterResp> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                RegisterReq,
                RegisterResp>(
                  this, METHODID_REGISTER)))
          .build();
    }
  }

  /**
   */
  public static final class RegisterStub extends io.grpc.stub.AbstractStub<RegisterStub> {
    private RegisterStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RegisterStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected RegisterStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RegisterStub(channel, callOptions);
    }

    /**
     */
    public void register(RegisterReq request,
                         io.grpc.stub.StreamObserver<RegisterResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RegisterBlockingStub extends io.grpc.stub.AbstractStub<RegisterBlockingStub> {
    private RegisterBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RegisterBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected RegisterBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RegisterBlockingStub(channel, callOptions);
    }

    /**
     */
    public RegisterResp register(RegisterReq request) {
      return blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RegisterFutureStub extends io.grpc.stub.AbstractStub<RegisterFutureStub> {
    private RegisterFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RegisterFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected RegisterFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RegisterFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<RegisterResp> register(
        RegisterReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RegisterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RegisterImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((RegisterReq) request,
              (io.grpc.stub.StreamObserver<RegisterResp>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RegisterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RegisterBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return RegisterProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Register");
    }
  }

  private static final class RegisterFileDescriptorSupplier
      extends RegisterBaseDescriptorSupplier {
    RegisterFileDescriptorSupplier() {}
  }

  private static final class RegisterMethodDescriptorSupplier
      extends RegisterBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RegisterMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RegisterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RegisterFileDescriptorSupplier())
              .addMethod(getRegisterMethod())
              .build();
        }
      }
    }
    return result;
  }
}
