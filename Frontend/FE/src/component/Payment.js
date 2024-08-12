import React, { useState } from 'react';
import axios from 'axios';

const Payment = () => {
    const userInfo = JSON.parse(localStorage.getItem("userInfo"));
  const [formData, setFormData] = useState({
    customer_id: userInfo.googleId,
    first_name: '',
    last_name: '',
    email: '',
    phone_number: '',
    address: '',
    created_at: new Date().toISOString(),
    order_delivered_carrler_date: new Date().toISOString(),
  });
  const cart = JSON.parse(localStorage.getItem("cart"))
  console.log("cart",cart);
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/orders', formData);
      const orderId = response.data.id;
      if(response.status===201){
        for (let item of cart) {
          await axios.post('http://localhost:8080/api/orderItems', {
            order_id: orderId,
            product_id: item.product_id,
            quantity: item.quantity,
            price: item.regular_price,
          });
        }
  localStorage.removeItem("cart");
  window.location.href = "/";
      }
      alert('Order created successfully!');
    } catch (error) {
      console.error('There was an error creating the order!', error);
    }
  };

  return (
    <div>
      <section className="section-content padding-y">
        <div className="container" style={{maxWidth: 720}}>
          <div className="card mb-4">
            <div className="card-body">
              <h4 className="card-title mb-3">Delivery info</h4>
              <div className="form-row">
                <div className="col form-group">
                  <label>First name</label>
                  <input type="text" className="form-control" name="first_name" value={formData.first_name} onChange={handleChange} />
                </div>
                <div className="col form-group">
                  <label>Last name</label>
                  <input type="text" className="form-control" name="last_name" value={formData.last_name} onChange={handleChange} />
                </div>
              </div>
              <div className="form-row">
                <div className="col form-group">
                  <label>Email</label>
                  <input type="email" className="form-control" name="email" value={userInfo.email} onChange={handleChange} />
                </div>
                <div className="col form-group">
                  <label>Phone</label>
                  <input type="text" className="form-control" name="phone_number" value={formData.phone_number} onChange={handleChange} />
                </div>
              </div>
              <div className="form-group">
                <label>Address</label>
                <textarea className="form-control" rows={2} name="address" value={formData.address} onChange={handleChange} />
              </div>
              <button className="subscribe btn btn-primary btn-block" type="button" onClick={handleSubmit}> Confirm</button>
            </div>
          </div>
          <br /><br />
        </div>
      </section>
    </div>
  );
}

export default Payment;
