import axios from "axios";
import React, { useEffect, useState } from "react";

const Order = () => {
    const [orders, setOrders] = useState([]);
  const [orderItems, setOrderItems] = useState([]);
  const [gallary, setGallary] = useState([]);
  const [product, setProduct] = useState([]);

  const userInfo = JSON.parse(localStorage.getItem("userInfo"));
  const IdUser = userInfo.googleId.toString();

  useEffect(() => {
    const fetchOrdersAndItems = async () => {
      try {
        const [orderResponse, orderItemResponse,gallerReponse,productsReponse] = await Promise.all([
          axios.get("http://localhost:8080/api/orders"),
            axios.get("http://localhost:8080/api/orderItems"),
            axios.get("http://localhost:8080/galleries"),
            axios.get("http://localhost:8080/products"),

        ]);

        setOrders(orderResponse.data);
        setOrderItems(orderItemResponse.data);
        setGallary(gallerReponse.data);
        setProduct(productsReponse.data);
      } catch (error) {
        console.error("Lỗi khi gọi API:", error);
      }
    };

    fetchOrdersAndItems();
  }, []);

  const filteredOrders = orders.filter((order) => order.customer_id.toString() === IdUser);

  const getOrderItemsByOrderId = (orderId) => {
    return orderItems.filter((item) => item.order_id === orderId);
  };

  const formatDate = (isoString) => {
    const date = new Date(isoString);
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Tháng trong JavaScript bắt đầu từ 0
    const year = date.getFullYear();
    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
    return `${day}-${month}-${year} ${hours}:${minutes}`;
  };
  function formatCurrency(number) {
    return new Intl.NumberFormat("vi-VN", {
      style: "currency",
      currency: "VND",
    }).format(number);
  }
  const getProductImages = (productId) => {
    const productImages = gallary.filter((image) => image.productId === productId && image.display_order === 1);
    return productImages.length > 0 ? productImages : []; // Trả về mảng chứa hình ảnh có display_order = 1 hoặc mảng rỗng nếu không có hình ảnh
};
const getProductName = (productId) => {
    const products = product.find((p) => p.product_id === productId);
    return products ? products.product_name : "Product not found";
  };
  return (
    <div>
      <section className="section-content padding-y">
        <div className="container">
          <div className="row">
            <main className="col-md">
                {filteredOrders.map((order) => (
                         <article className="card mb-4">
                         <header className="card-header">
                           <a href="#" className="float-right">
                             {" "}
                             <i className="fa fa-print" /> Print
                           </a>
                           <strong className="d-inline-block mr-3">
                             Order ID: {order.customer_id}
                           </strong>
                           <span>Order Date: {formatDate(order.order_delivered_carrler_date)}</span>
                         </header>
                         <div className="card-body">
                           <div className="row">
                             <div className="col-md-8">
                               <h6 className="text-muted">Delivery to</h6>
                               <p>
                                 {order.last_name} {order.first_name} <br />
                                 Phone {order.phone_number} Email: {order.email} <br />
                                 Location: {order.address} <br />
                               </p>
                             </div>
                             {/* <div className="col-md-4">
                               <h6 className="text-muted">Payment</h6>
                               <span className="text-success">
                                 <i className="fab fa-lg fa-cc-visa" />
                                 Visa **** 4216
                               </span>
                               <p>
                                 Subtotal: $356 <br />
                                 Shipping fee: $56 <br />
                                 <span className="b">Total: $456 </span>
                               </p>
                             </div> */}
                           </div>{" "}
                           {/* row.// */}
                         </div>{" "}
                         {/* card-body .// */}
                         <div className="table-responsive">
                           <table className="table table-hover">
                             <tbody>
                             {getOrderItemsByOrderId(order.id).map((item) => (
                               <tr>
                                 <td width={65}>
                                 {getProductImages(item.product_id).map((image, index) => (
                        <img
                          key={index}
                          className="img-xs border"
                          src={`${image.image_path}`}
      
                        />
                      ))}
                       
                                 </td>
                                 <td>
                                   <p className="title mb-0">                                {getProductName(item.product_id)}
                                   </p>
                                   <var className="price text-muted">{formatCurrency(item.price)}</var>
                                 </td>
                                 <td width={250}>
                                   {" "}
                                   <a href="#" className="btn btn-outline-primary">
                                     Track order
                                   </a>
                                   <div className="dropdown d-inline-block">
                                     <a
                                       href="#"
                                       data-toggle="dropdown"
                                       className="dropdown-toggle btn btn-outline-secondary"
                                     >
                                       More
                                     </a>
                                     <div className="dropdown-menu dropdown-menu-right">
                                       <a href="#" className="dropdown-item">
                                         Return
                                       </a>
                                       <a href="#" className="dropdown-item">
                                         Cancel order
                                       </a>
                                     </div>
                                   </div>{" "}
                                   {/* dropdown.// */}
                                 </td>
                               </tr>
                 ))}
                             </tbody>
                           </table>
                         </div>{" "}
                         {/* table-responsive .end// */}
                       </article>
                ))}
         
              {/* card order-item .// */}
            </main>{" "}
            {/* col.// */}
          </div>
        </div>{" "}
        {/* container .//  */}
      </section>
    </div>
  );
};

export default Order;
