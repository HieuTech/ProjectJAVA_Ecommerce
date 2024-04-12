package business.implement;

import business.design.IProductDesign;
import business.entity.Product;
import business.utils.IOFile;
import business.utils.InputMethods;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProductImplement implements IProductDesign {
    public static List<Product> productList;

    static {
        productList = IOFile.readFromFile(IOFile.PRODUCT_PATH);
    }

    @Override
    public void displayProductList() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p2.getCreatedAt().compareTo(p1.getCreatedAt());
            }
        });

        for (Product product : productList) {
            if (product.isStatus()) {
                product.displayData();
            }
        }
    }

    @Override
    public void addNewProduct() {
        Product product = new Product();
        product.inputData();

//        boolean categoryExists = false;
//        for (Product p : ProductImplement.productList) {
//            if (p.getCategoryId().equals(product.getCategoryId())) {
//                categoryExists = true;
//                break;
//            }
//        }

//        if (!categoryExists) {
//            System.out.println("Vui lòng tạo danh mục trước khi thêm sản phẩm!");
//            return;
//        }

        int maxId = 0;
        for (Product p : productList) {
            if (p.getProductId() > maxId) {
                maxId = p.getProductId();
            }
        }
        productList.add(product);
        IOFile.writeToFile(IOFile.PRODUCT_PATH, productList);
    }

    @Override
    public void editProductInfo() {
        System.out.println("Nhập mã sản phẩm cần chỉnh sửa: ");
        int productId = InputMethods.getInt();
        boolean isFound = false;
        Product productToUpdate;
        for (Product p : productList) {
            if (p.getProductId() == productId) {
                productToUpdate = p;
                productToUpdate.inputData();
                productToUpdate.setUpdatedAt(LocalDate.now());
                IOFile.writeToFile(IOFile.PRODUCT_PATH, productList);
                System.out.println("Thông tin sản phẩm đã được cập nhật.");
                break;
            }else{
                isFound = true;
            }
        }

//        if (productToUpdate != null) {
//            productToUpdate.inputData();
//            productToUpdate.setUpdatedAt(LocalDate.now());
//            IOFile.writeToFile(IOFile.PRODUCT_PATH, productList);
//
//        }
       if(isFound) {
            System.out.println("Không tìm thấy sản phẩm có mã là " + productId);
        }
    }

    @Override
    public void hideProductById() {
        System.out.println("Nhập mã sản phẩm cần ẩn: ");
        int productId = InputMethods.getInt();

        Product productToHide = null;
        for (Product p : productList) {
            if (p.getProductId() == productId) {
                productToHide = p;
                break;
            }
        }

        if (productToHide != null) {
            productToHide.setStatus(false);
            IOFile.writeToFile(IOFile.PRODUCT_PATH, productList);
            System.out.println("Sản phẩm đã được ẩn.");
        } else {
            System.out.println("Không tìm thấy sản phẩm có mã là " + productId);
        }
    }

    @Override
    public void searchProductByName() {
        System.out.println("Nhập tên sản phẩm cần tìm kiếm: ");
        String searchName = InputMethods.getString().toLowerCase();

        boolean check = false;

        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(searchName) && product.isStatus()) {
                product.displayData();
                check = true;
            }
        }

        if (!check) {
            System.out.println("Không tìm thấy sản phẩm nào có tên chứa '" + searchName + "' hoặc sản phẩm đã bị ẩn.");
        }
    }

    @Override
    public void searchProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên sản phẩm cần tìm kiếm: ");
        String searchName = scanner.nextLine().toLowerCase();

        boolean check = false;

        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(searchName) && product.isStatus()) {
                product.displayData();
                check = true;
            }
        }

        if (!check) {
            System.out.println("Không tìm thấy sản phẩm nào có tên chứa '" + searchName + "' hoặc sản phẩm đã bị ẩn.");
        }
    }

    @Override
    public void displayTop10Product() {
        List<Product> topProducts = new ArrayList<>(productList);
        Collections.sort(topProducts, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Integer.compare(p2.getSoldCount(), p1.getSoldCount());
            }
        });

        int count = 0;
        for (Product product : topProducts) {
            if (product.isStatus()) {
                product.displayData();
                count++;
                if (count >= 10) {
                    break;
                }
            }
        }
    }

    @Override
    public void displayProductGroupByCatalog() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã danh mục sản phẩm: ");
        String categoryId = scanner.nextLine();

        boolean check = false;
        for (Product product : productList) {
            if (product.getCategoryId().equals(categoryId) && product.isStatus()) {
                product.displayData();
                check = true;
            }
        }

        if (!check) {
            System.out.println("Không tìm thấy sản phẩm nào thuộc danh mục có mã '" + categoryId + "' hoặc sản phẩm đã bị ẩn.");
        }
    }

    @Override
    public void showProductList() {
        System.out.println("Danh sách sản phẩm:");

        for (Product product : productList) {
            if (product.isStatus()) {
                product.displayData();
            }
        }
    }

    @Override
    public void displayProductByPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sắp xếp sản phẩm theo giá:");
        System.out.println("1. Giá tăng dần");
        System.out.println("2. Giá giảm dần");
        System.out.println("Nhập lựa chọn: ");
        int choice = Integer.parseInt(scanner.nextLine());

        List<Product> sortedList = new ArrayList<>(productList);
        if (choice == 1) {
            Collections.sort(sortedList, new Comparator<Product>() {
                @Override
                public int compare(Product p1, Product p2) {
                    return Double.compare(p1.getUnitPrice(), p2.getUnitPrice());
                }
            });
        } else if (choice == 2) {
            Collections.sort(sortedList, new Comparator<Product>() {
                @Override
                public int compare(Product p1, Product p2) {
                    return Double.compare(p2.getUnitPrice(), p1.getUnitPrice());
                }
            });
        } else {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }

        for (Product product : sortedList) {
            if (product.isStatus()) {
                product.displayData();
            }
        }
    }

    @Override
    public void displayProductById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã sản phẩm cần xem chi tiết: ");
        int productId = scanner.nextInt();

        boolean check = false;
        for (Product product : productList) {
            if (product.getProductId() == productId) {
                product.displayData();
                check = true;
                break;
            }
        }

        if (!check) {
            System.out.println("Không tìm thấy sản phẩm có mã là " + productId);
        }
    }
}