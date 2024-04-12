package presentation.admin;

import business.design.ICatalogDesign;
import business.implement.CatalogImplement;
import business.utils.InputMethods;

public class CatalogManagement {
    ICatalogDesign catalogImplement = new CatalogImplement();
    public void displayCatalogMenu() {
       while (true) {
           System.out.println("==========QUẢN LÝ DANH MỤC==========");
           System.out.println("1. Hiển thị danh sách danh mục");
           System.out.println("2. Tạo mới danh mục");
           System.out.println("3. Tìm kiếm danh mục theo tên");
           System.out.println("4. Chỉnh sửa thông tin danh mục");
           System.out.println("5. Ẩn danh mục theo mã danh mục");
           System.out.println("6. Thoát");
           System.out.println("Nhập lựa chọn: ");
           byte choice = InputMethods.getByte();
           switch (choice) {
               case 1:
                   catalogImplement.displayCatalogList();
                   break;
               case 2:
                   catalogImplement.addNewCatalog();
                   break;
               case 3:
                   catalogImplement.searchCatalogByName();
                   break;
               case 4:
                   catalogImplement.editCatalogInfo();
                   break;
               case 5:
                   catalogImplement.hideCatalogbyId();
                   break;
               case 6:
                   return;
               default:
                   System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại");
           }
       }
    }
}
