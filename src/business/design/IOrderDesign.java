package business.design;

public interface IOrderDesign {

    void displayUncomfirmedOrderList();

    void displayComfirmedOrderList();

    void confirmOrderWaiting();

    void cancelOrder();
}