package com.wrapper.spotify;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.apache.http.Header;
import org.apache.http.NameValuePair;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * A simple HTTP connection interface.
 */
public interface IHttpManager {

  /**
   * Perform an HTTP GET request to the specified URL.
   *
   * @param uri     The GET request's {@link URI}.
   * @param headers The GET request's {@link Header}s.
   * @return A string containing the GET request's response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  String get(URI uri, Header[] headers) throws
          IOException,
          SpotifyWebApiException;

  /**
   * Perform an HTTP POST request to the specified URL.
   *
   * @param uri            The POST request's {@link URI}.
   * @param headers        The POST request's {@link Header}s.
   * @param postParameters The POST request's form parameters as a {@link List} of {@link NameValuePair}s.
   * @return A string containing the POST request's response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  String post(URI uri, Header[] headers, List<NameValuePair> postParameters) throws
          IOException,
          SpotifyWebApiException;

  /**
   * Perform an HTTP PUT request to the specified URL.
   *
   * @param uri           The PUT request's {@link URI}.
   * @param headers       The PUT request's {@link Header}s.
   * @param putParameters The PUT request's form parameters as a {@link List} of {@link NameValuePair}s.
   * @return A string containing the PUT request's response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  String put(URI uri, Header[] headers, List<NameValuePair> putParameters) throws
          IOException,
          SpotifyWebApiException;

  /**
   * Perform an HTTP DELETE request to the specified URL.
   *
   * @param uri     The DELETE request's {@link URI}.
   * @param headers The DELETE request's {@link Header}s.
   * @return A string containing the DELETE request's response body.
   * @throws IOException            In case of networking issues.
   * @throws SpotifyWebApiException The Web API returned an error further specified in this exception's root cause.
   */
  String delete(URI uri, Header[] headers) throws
          IOException,
          SpotifyWebApiException;

}
