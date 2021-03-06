package com.wrapper.spotify.requests;

import com.wrapper.spotify.IHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.apache.http.Header;
import org.apache.http.NameValuePair;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.Future;

public interface IRequest {

  IHttpManager getHttpManager();

  URI getUri();

  List<Header> getHeaders();

  List<NameValuePair> getFormParameters();

  List<NameValuePair> getBodyParameters();

  String getBody();

  <T> T execute() throws
          IOException,
          SpotifyWebApiException;

  <T> Future<T> executeAsync();

  String getJson() throws
          IOException,
          SpotifyWebApiException;

  String postJson() throws
          IOException,
          SpotifyWebApiException;

  String putJson() throws
          IOException,
          SpotifyWebApiException;

  String deleteJson() throws
          IOException,
          SpotifyWebApiException;

  interface Builder {

    Builder setHttpManager(final IHttpManager httpManager);

    Builder setScheme(final String scheme);

    Builder setHost(final String host);

    Builder setPort(final Integer port);

    Builder setPath(final String path);

    Builder setPathParameter(final String name, final String value);

    Builder setDefaults(final IHttpManager httpManager,
                        final String scheme,
                        final String host,
                        final Integer port);

    <T> Builder setQueryParameter(final String name, final T value);

    <T> Builder setHeader(final String name, final T value);

    <T> Builder setFormParameter(final String name, final T value);

    <T> Builder setBodyParameter(final String name, final T value);

    Builder setBody(final String value);

    AbstractRequest build();
  }
}
