// **********************************************************************
//
// Copyright (c) 2003-2015 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.1
//
// <auto-generated>
//
// Generated from file `file.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package com.lpmas.file.component;

public interface FileServicePrx extends Ice.ObjectPrx
{
    public String rpc(String method, String params);

    public String rpc(String method, String params, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_rpc(String method, String params);

    public Ice.AsyncResult begin_rpc(String method, String params, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_rpc(String method, String params, Ice.Callback __cb);

    public Ice.AsyncResult begin_rpc(String method, String params, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_rpc(String method, String params, Callback_FileService_rpc __cb);

    public Ice.AsyncResult begin_rpc(String method, String params, java.util.Map<String, String> __ctx, Callback_FileService_rpc __cb);

    public Ice.AsyncResult begin_rpc(String method, 
                                     String params, 
                                     IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_rpc(String method, 
                                     String params, 
                                     IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                     IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_rpc(String method, 
                                     String params, 
                                     java.util.Map<String, String> __ctx, 
                                     IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_rpc(String method, 
                                     String params, 
                                     java.util.Map<String, String> __ctx, 
                                     IceInternal.Functional_GenericCallback1<String> __responseCb, 
                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                     IceInternal.Functional_BoolCallback __sentCb);

    public String end_rpc(Ice.AsyncResult __result);
}