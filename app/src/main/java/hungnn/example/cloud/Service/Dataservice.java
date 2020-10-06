package hungnn.example.cloud.Service;

import hungnn.example.cloud.Model.Album;
import hungnn.example.cloud.Model.Baihat;
import hungnn.example.cloud.Model.ChuDe;
import hungnn.example.cloud.Model.Playlist;
import hungnn.example.cloud.Model.Quangcao;
import hungnn.example.cloud.Model.TheLoai;
import hungnn.example.cloud.Model.Theloaitrongngay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<Theloaitrongngay> GetCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();


    @GET("baihatduocthich.php")
    Call<List<Baihat>> GetBaiHatHot();
//_________________________________________________________________________

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaitheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> GetDanhsachcacPlaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);

//    @FormUrlEncoded
//    @POST("danhsachbaihat.php")
//    Call<List<Baihat>> GetDanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);


    @GET("tatcachude.php")
    Call<List<ChuDe>> GetAllChuDe();
//    ---------------------------------------------------------------

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetTheloaitheochude(@Field("idchude") String idchude);



    @GET("tatcaalbum.php")
    Call<List<Album>> GetAllAlbum();


//-----------------------



    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachbaihattheoalbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")// ban la crush thu n cua bai hat nay
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Baihat>> GetSearchBaiHat(@Field("tukhoa") String tukhoa);


}


