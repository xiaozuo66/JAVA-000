package io.kimmking.rpcfx.api;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class RpcfxRequest<T>{

  private Class<T> serviceClass;

  private String method;

  private Object[] params;

}
