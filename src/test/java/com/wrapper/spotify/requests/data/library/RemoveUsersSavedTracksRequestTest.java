package com.wrapper.spotify.requests.data.library;

import com.google.gson.JsonParser;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.data.AbstractDataTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.wrapper.spotify.Assertions.assertHasBodyParameter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class RemoveUsersSavedTracksRequestTest extends AbstractDataTest<String> {
  private final RemoveUsersSavedTracksRequest defaultRequest = SPOTIFY_API
          .removeUsersSavedTracks(ID_TRACK, ID_TRACK)
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/library/RemoveUsersSavedTracksRequest.json"))
          .build();
  private final RemoveUsersSavedTracksRequest bodyRequest = SPOTIFY_API
          .removeUsersSavedTracks(new JsonParser()
                  .parse("[\"" + ID_TRACK + "\",\"" + ID_TRACK + "\"]").getAsJsonArray())
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/follow/FollowArtistsOrUsersRequestTest.json"))
          .build();

  public RemoveUsersSavedTracksRequestTest() throws Exception {
  }

  @Test
  public void shouldComplyWithReference() {
    assertHasAuthorizationHeader(defaultRequest);
    assertEquals(
            "https://api.spotify.com:443/v1/me/tracks?ids=01iyCAUm8EvOFqVWYJ3dVX%2C01iyCAUm8EvOFqVWYJ3dVX",
            defaultRequest.getUri().toString());

    assertHasAuthorizationHeader(bodyRequest);
    assertHasBodyParameter(
            bodyRequest,
            "ids",
            "[\"" + ID_TRACK + "\",\"" + ID_TRACK + "\"]");
    assertEquals(
            "https://api.spotify.com:443/v1/me/tracks",
            bodyRequest.getUri().toString());
  }

  @Test
  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException {
    shouldReturnDefault(defaultRequest.execute());
  }

  @Test
  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
    shouldReturnDefault((String) defaultRequest.executeAsync().get());
  }

  public void shouldReturnDefault(final String string) {
    assertNull(
            string);
  }
}
