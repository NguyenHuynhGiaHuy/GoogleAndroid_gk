DEMO MỘT SỐ CHỨC NĂNG 
2.1 Chức năng hiển thị các dạng bản đồ trong google map
Cách thực hiện demo:
1/Tạo ứng dụng Android:  sử dụng Android Studio để tạo ứng dụng
2/Thêm Google Maps API:
Đầu tiên chắc chắn rằng ứng dụng đã có quyền truy cập Google Maps API. Điều này yêu cầu có một khóa API (API key) của Google Maps. Truy cập Google Cloud Console để lấy khóa API
3/Thêm hỗ trợ bản đồ:Thêm SupportMapFragment vào layout

![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/1b7efcbf-bd5b-490b-96f7-6593766fed49)



4/Cấu hình Google Maps API:
Trong tệp build.gradle của ứng dụng, thêm dependency cho Google Maps API:

![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/2d9e22cc-98db-442b-b264-f9c978bcf825)



5/Thêm các hành động của mã vào Activity hoặc Fragment:


![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/9dbde6e6-ea03-46e5-94a5-eb823af66537)






6/Chạy ứng dụng:


![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/0cb1b679-76b1-4847-90f1-17f960998df5)



Hình 2.1.1 Hiển thị các dạng bản đồ trong google map


![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/4baf2943-3461-4a00-aa86-0536097c6922)



Hình 2.1.2 Hiển thị các dạng bản đồ trong google map

![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/d02a67cd-3bcc-43a7-a73c-74fbb36f43ab)



Hình 2.1.3 Hiển thị các dạng bản đồ trong google map

![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/15478f9b-bff4-4d37-a909-df8b1a8cc751)





Hình 2.1.4 Hiển thị các dạng bản đồ trong google map





2.2 Chức năng tìm kiếm địa điểm:
1/Thêm SearchView vào layout XML

![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/6d8d8766-20e4-466e-82e7-79effd2a5fb1)




2/Ánh xạ và Lắng nghe Sự kiện

![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/836a4474-a510-4a60-afaa-afb9ac5b1589)



3/Xử lý Tìm kiếm khi người dùng Gửi yêu cầu (onQueryTextSubmit)


![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/3c878c83-e730-4d46-a095-ffb5ddc3386a)


4/Chạy code



![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/90aea70c-357d-4f85-a5a1-7027659b8493)





Hình 2.2.1 Tìm kiếm địa điểm

2.3 Chức năng đánh dấu vị trí :


1/Thêm phương thức 


![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/5fbd3bc8-5f7f-4c96-a05b-42a05766ed10)



2/Chạy demo


![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/0ea0476e-f689-42f9-adf6-f70ef3e8d1b9)



Hình 2.3.1 Đánh dấu vị trí


![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/dd6a0b5a-3fd4-44c3-9be3-c3a765e83984)




Hình 2.3.2 Đánh dấu vị trí




2.4 Chức năng lấy vị trí hiện tại:
1/ Thêm các thư viện và imlement cần thiết



![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/cf52d837-0ee3-4804-a96f-41a49a90e174)



2/Button để hiển thị vị trí hiện tại của người dùng


![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/e4c16b69-b67b-4d8c-8f70-85884654e4c1)



3/Thêm Phương thức để lấy vị trí hiện tại của người dùng


![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/284d40d2-a118-4e4c-bfb5-0f1d41ff9677)



4/Xử lý khi người dùng cấp quyền truy cập vị trí


![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/064035fc-a8e5-4b80-8461-055df816ee33)




5/Chạy demo


![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/1ae81f02-37d7-4606-a195-ecfe9b2b64ed)




Hình 2.4.1 Lấy vị trí hiện tại
Sau khi nhấn lấy vị trí :


![image](https://github.com/NguyenHuynhGiaHuy/GoogleAndroid_gk/assets/130195149/bb66df36-7994-4aaa-8698-3699dc1dbe88)


Hình 2.4.2 Sau khi lấy vị trí

Sơ đồ MindMap:
 https://cacoo.com/diagrams/1zEgT5rxkH50xGxo/E5868
