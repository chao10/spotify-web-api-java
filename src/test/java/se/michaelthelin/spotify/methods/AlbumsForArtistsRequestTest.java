package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.HttpManager;
import se.michaelthelin.spotify.TestUtil;
import se.michaelthelin.spotify.models.Album;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class AlbumsForArtistsRequestTest {

  @Test
  public void shouldGetAlbumResultForArtistId_async() throws Exception {
    final Api api = Api.DEFAULT_API;

    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("albums.json");
    final AlbumsForArtistRequest request = api.albumsForArtist("53A0W3U0s8diEn9RhXQhVz").httpManager(mockedHttpManager).build();

    final CountDownLatch asyncCompleted = new CountDownLatch(1);

    final SettableFuture<List<Album>> albumsFuture = request.getAlbumsAsync();

    Futures.addCallback(albumsFuture, new FutureCallback<List<Album>>() {
      @Override
      public void onSuccess(List<Album> albums) {
        assertEquals(1, albums.size());

        Album firstAlbum = albums.get(0);
        assertEquals("41MnTivkwTO3UUJ8DrqEJJ", firstAlbum.getId());

        asyncCompleted.countDown();
      }

      @Override
      public void onFailure(Throwable throwable) {
        fail("Failed to resolve future");
      }
    });

    asyncCompleted.await(1, TimeUnit.SECONDS);
  }

  @Test
  public void shouldGetAlbumResultForArtistId_sync() throws Exception {
    final Api api = Api.DEFAULT_API;

    final HttpManager mockedHttpManager = TestUtil.MockedHttpManager.returningJson("albums.json");
    final AlbumsForArtistRequest request = api.albumsForArtist("53A0W3U0s8diEn9RhXQhVz").httpManager(mockedHttpManager).build();

    final List<Album> albums = request.getAlbums();

    assertEquals(1, albums.size());

    final Album firstAlbum = albums.get(0);
    assertEquals("41MnTivkwTO3UUJ8DrqEJJ", firstAlbum.getId());
  }

}