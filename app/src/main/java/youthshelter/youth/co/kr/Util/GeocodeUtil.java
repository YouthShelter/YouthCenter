package youthshelter.youth.co.kr.Util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GeocodeUtil {
  final Geocoder geocoder;
  private LocationManager locationManager;

  public static class GeoLocation {

    double latitude;
    double longitude;

    public GeoLocation(double latitude, double longitude) {
      this.latitude = latitude;
      this.longitude = longitude;
    }

    public double getLatitude() {
      return latitude;
    }

    public void setLatitude(double latitude) {
      this.latitude = latitude;
    }

    public double getLongitude() {
      return longitude;
    }

    public void setLongitude(double longitude) {
      this.longitude = longitude;
    }
  }

  private LocationManager locManager;
  private Location myLocation;

  public GeocodeUtil(Context context) {
    locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    geocoder = new Geocoder(context, Locale.KOREAN);

  }

  public void requestMyLocation(LocationListener locationListener) {
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, locationListener);
  }

  public ArrayList<GeoLocation> getGeoLocationListUsingAddress(String address) {
    ArrayList<GeoLocation> resultList = new ArrayList<>();
    try {
      List<Address> list = geocoder.getFromLocationName(address, 10);

      for (Address addr : list) {
        resultList.add(new GeoLocation(addr.getLatitude(), addr.getLongitude()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return resultList;
  }

  public ArrayList<String> getAddressListUsingGeolocation(GeoLocation location) {
    ArrayList<String> resultList = new ArrayList<>();

    try {
      List<Address> list = geocoder.getFromLocation(location.latitude, location.longitude, 10);

      for (Address addr : list) {
        resultList.add(addr.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return resultList;
  }

  private final LocationListener mLocationListener = new LocationListener() {
    public void onLocationChanged(Location location) {
      //여기서 위치값이 갱신되면 이벤트가 발생한다.
      //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.

      Log.d("test", "onLocationChanged, location:" + location);
      double longitude = location.getLongitude(); //경도
      double latitude = location.getLatitude();   //위도
      double altitude = location.getAltitude();   //고도
      float accuracy = location.getAccuracy();    //정확도
      String provider = location.getProvider();   //위치제공자
      //Gps 위치제공자에 의한 위치변화. 오차범위가 좁다.
      //Network 위치제공자에 의한 위치변화
      //Network 위치는 Gps에 비해 정확도가 많이 떨어진다.
      //tv.setText("위치정보 : " + provider + "\n위도 : " + longitude + "\n경도 : " + latitude + "\n고도 : " + altitude + "\n정확도 : "  + accuracy);
    }
    public void onProviderDisabled(String provider) {
      // Disabled시
      Log.d("test", "onProviderDisabled, provider:" + provider);
    }

    public void onProviderEnabled(String provider) {
      // Enabled시
      Log.d("test", "onProviderEnabled, provider:" + provider);
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
      // 변경시
      Log.d("test", "onStatusChanged, provider:" + provider + ", status:" + status + " ,Bundle:" + extras);
    }
  };
}