package business.implement;

import business.design.ICatalogDesign;
import business.entity.Catalog;
import business.utils.IOFile;

import java.util.List;
import java.util.Scanner;

public class CatalogImplement implements ICatalogDesign {
    public static List<Catalog> catalogList;

    static {
        catalogList = IOFile.readFromFile(IOFile.CATALOG_PATH);
    }

    @Override
    public void displayCatalogList() {
        System.out.println("==========DANH SÁCH DANH MỤC==========");
        catalogList.stream()
                .sorted((c1, c2) -> c1.getCatalogId().compareTo(c2.getCatalogId()))
                .forEach(catalog -> catalog.displayData());
    }

    @Override
    public void addNewCatalog() {
        Scanner scanner = new Scanner(System.in);
        Catalog catalog = new Catalog();
        catalog.inputData();
        catalogList.add(catalog);
        IOFile.writeToFile(IOFile.CATALOG_PATH, catalogList);
    }

    @Override
    public void searchCatalogByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên danh mục cần tìm: ");
        String searchName = scanner.nextLine().toLowerCase();
        System.out.println("==========KẾT QUẢ TÌM KIẾM==========");
        catalogList.stream()
                .filter(catalog -> catalog.getCatalogName().toLowerCase().contains(searchName))
                .forEach(catalog -> catalog.displayData());
    }

    @Override
    public void editCatalogInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên danh mục cần chỉnh sửa thông tin: ");
        String catalogName = scanner.nextLine();
        Catalog catalog = catalogList.stream()
                .filter(c -> c.getCatalogName().equals(catalogName))
                .findFirst().orElse(null);
        if (catalog != null) {
            System.out.println("Nhập mô tả/thông số mới: ");
            String newDescription = scanner.nextLine();
            catalog.setDescription(newDescription);
            IOFile.writeToFile(IOFile.CATALOG_PATH, catalogList);
            System.out.println("Thông tin danh mục đã được chỉnh sửa!");
        } else {
            System.out.println("Không tìm thấy danh mục!");
        }
    }

    @Override
    public void hideCatalogbyId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã danh mục cần ẩn: ");
        String catalogId = scanner.nextLine();
        Catalog catalog = catalogList.stream()
                .filter(c -> c.getCatalogId().equals(catalogId))
                .findFirst().orElse(null);
        if (catalog != null) {
            catalog.setStatus(false);
            IOFile.writeToFile(IOFile.CATALOG_PATH, catalogList);
            System.out.println("Danh mục đã được ẩn!");
        } else {
            System.out.println("Không tìm thấy danh mục!");
        }
    }
}