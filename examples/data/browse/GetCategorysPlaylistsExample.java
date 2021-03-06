package data.browse;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.browse.GetCategorysPlaylistsRequest;

import java.util.concurrent.Future;

public class GetCategorysPlaylistsExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
  private static final String categoryId = "dinner";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetCategorysPlaylistsRequest getCategoryRequest = spotifyApi.getCategorysPlaylists(categoryId)
          .country(CountryCode.SE)
          .limit(10)
          .offset(0)
          .build();

  public static void getCategorysPlaylists_Sync() {
    try {
      final Paging<PlaylistSimplified> playlistSimplifiedPaging = getCategoryRequest.execute();

      System.out.println("Total: " + playlistSimplifiedPaging.getTotal());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }

  public static void getCategorysPlaylists_Async() {
    try {
      final Future<Paging<PlaylistSimplified>> pagingFuture = getCategoryRequest.executeAsync();

      // ...

      final Paging<PlaylistSimplified> playlistSimplifiedPaging = pagingFuture.get();

      System.out.println("Total: " + playlistSimplifiedPaging.getTotal());
    } catch (Exception e) {
      System.out.println("Something went wrong!\n" + e.getMessage());
    }
  }
}