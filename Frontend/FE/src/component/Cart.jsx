import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';

const Cart = () => {
    const [cartItems, setCartItems] = useState([]);

    useEffect(() => {
      const fetchCartItems = () => {
        try {
          const cart = JSON.parse(localStorage.getItem('cart')) || [];
          setCartItems(cart);
        } catch (error) {
          console.error('Lỗi khi lấy sản phẩm từ giỏ hàng:', error);
        }
      };
  
      fetchCartItems();
    }, []);
    function formatCurrency(number) {
        return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(number);
    }
    const removeFromCart = (productId) => {
        try {
          let cart = JSON.parse(localStorage.getItem('cart')) || [];
          const updatedCart = cart.filter(item => item.product_id !== productId); 
          localStorage.setItem('cart', JSON.stringify(updatedCart));
          console.log("Sản phẩm đã được xóa khỏi giỏ hàng, ID sản phẩm:", productId);
          setCartItems(updatedCart);
        } catch (error) {
          console.error("Lỗi khi xóa sản phẩm khỏi giỏ hàng:", error);
        }
      };
      const handleQuantityChange = (item, newQuantity) => {
  try {
    // Lấy danh sách sản phẩm từ local storage
    let cart = JSON.parse(localStorage.getItem('cart')) || [];

    // Tìm kiếm sản phẩm cần cập nhật trong giỏ hàng và cập nhật số lượng mới
    const updatedCart = cart.map(cartItem => {
      if (cartItem.product_id === item.product_id) {
        // Đảm bảo số lượng không nhỏ hơn 1
        const quantity = Math.max(newQuantity, 1);
        // Trả về một đối tượng mới với số lượng đã cập nhật
        return { ...cartItem, quantity: quantity };
      }
      return cartItem;
    });

    // Lưu danh sách giỏ hàng mới vào local storage
    localStorage.setItem('cart', JSON.stringify(updatedCart));

    // Cập nhật state với giỏ hàng mới
    setCartItems(updatedCart);
  } catch (error) {
    console.error("Lỗi khi cập nhật số lượng sản phẩm:", error);
  }
};

  return (
  <>
  <section className="section-content padding-y">
  <div className="container">
    <div className="row">
      <main className="col-md-9">
        <div className="card">
          <table className="table table-borderless table-shopping-cart">
            <thead className="text-muted">
              <tr className="small text-uppercase">
                <th scope="col">Product</th>
                <th scope="col" width={120}>Quantity</th>
                <th scope="col" width={120}>Price</th>
                <th scope="col" className="text-right" width={200}> </th>
              </tr>
            </thead>
            <tbody>
            {cartItems.map((item, index) => (
      <tr key={index}>
        <td className='widthInput'>
          <figure className="itemside">
            <div className="aside"><img src={`asset/images/items/${item.sku}`} className="img-sm" alt={item.product_name} /></div>
            <figcaption className="info">
              <a href="#" className="title text-dark">{item.product_name}</a>

            </figcaption>
          </figure>
        </td>
        <td className='widthInputv'> 
        <div className="input-group">
    <button
      className="btn btn-outline-secondary"
      type="button"
      onClick={() => handleQuantityChange(item, item.quantity - 1)}
    >
      -
    </button>
    <input
      type="number"
      className="form-control text-center"
      value={item.quantity}
      readOnly
    />
    <button
      className="btn btn-outline-secondary"
      type="button"
      onClick={() => handleQuantityChange(item, item.quantity + 1)}
    >
      +
    </button>
  </div>
        </td>
        <td > 
          <div className="price-wrap"> 
            <var className="price">{formatCurrency(item.regular_price)}</var> 
            <small className="text-muted">{formatCurrency((item.regular_price)*(item.quantity))} each</small> 
          </div> {/* price-wrap .// */}
        </td>
        <td className="text-right"> 
          <button className="btn btn-light" onClick={() => removeFromCart(item.product_id)}>Remove</button>
        </td>
      </tr>
    ))}
              
            </tbody>
          </table>
          <div className="card-body border-top">
            <a href="#" className="btn btn-primary float-md-right"> Make Purchase <i className="fa fa-chevron-right" /> </a>
            <Link to={`/home`} className="btn btn-light"> <i className="fa fa-chevron-left" /> Continue shopping </Link>
          </div>	
        </div> {/* card.// */}
        <div className="alert alert-success mt-3">
          <p className="icontext"><i className="icon text-success fa fa-truck" /> Free Delivery within 1-2 weeks</p>
        </div>
      </main> {/* col.// */}
      <aside className="col-md-3">
        <div className="card mb-3">
          <div className="card-body">
            <form>
              <div className="form-group">
                <label>Have coupon?</label>
                <div className="input-group">
                  <input type="text" className="form-control" name placeholder="Coupon code" />
                  <span className="input-group-append"> 
                    <button className="btn btn-primary">Apply</button>
                  </span>
                </div>
              </div>
            </form>
          </div> {/* card-body.// */}
        </div>  {/* card .// */}
        <div className="card">
          <div className="card-body">
            <dl className="dlist-align">
              <dt>Total price:</dt>
              <dd className="text-right">USD 568</dd>
            </dl>
            <dl className="dlist-align">
              <dt>Discount:</dt>
              <dd className="text-right">USD 658</dd>
            </dl>
            <dl className="dlist-align">
              <dt>Total:</dt>
              <dd className="text-right  h5"><strong>$1,650</strong></dd>
            </dl>
            <hr />
            <p className="text-center mb-3">
              <img src="images/misc/payments.png" height={26} />
            </p>
          </div> {/* card-body.// */}
        </div>  {/* card .// */}
      </aside> {/* col.// */}
    </div>
  </div> {/* container .//  */}
</section>

  </>
  )
}

export default Cart
