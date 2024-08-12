import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import GoogleLogin from 'react-google-login';
const clientId = "349196289553-cbok7vjqcr257sp3qp82vtlnm05704rl.apps.googleusercontent.com";

const Login = () => {
  const onSuccess = async (response) => {
    console.log('Login Success! User info: ', response.profileObj);
    const userInfo = response.profileObj;
    localStorage.setItem('userInfo', JSON.stringify(userInfo));
    localStorage.setItem('loggedIn', true);
    const IdUserLogin = userInfo.googleId;

    // Xóa giỏ hàng hiện tại trước khi lấy giỏ hàng mới
    localStorage.removeItem('cart');
let cardId;
    try {
      const responseCart = await axios.get(`http://localhost:8080/api/cards/search?google_customer=${IdUserLogin}`);
      if (responseCart.status === 200) {
        // Card exists, retrieve card_id
      cardId = responseCart.data.card_id;
      }

      // Lấy các sản phẩm trong giỏ hàng và lưu trữ vào localStorage
      const cartItems = await axios.get(`http://localhost:8080/api/CardItems/byCard/${cardId}`);
      const datacart=cartItems.data;
      console.log('====================================');
      console.log(datacart,"data");
      console.log('====================================');
      localStorage.setItem('cart', JSON.stringify(datacart));

      window.location.href = "/";
    } catch (error) {
      console.error('Lỗi khi gọi API:', error);
    }
  };

  const fetchCartItemsByCardId = async (cardId) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/CardItems/byCard/${cardId}`);
      return response.data;
    } catch (error) {
      console.error("Lỗi khi gọi API:", error);
      return [];
    }
  };

  const onFailure = (response) => {
    console.log('Login failure', response);
  };
  
    // const [email, setEmail] = useState('');
    // const [password, setPassword] = useState('');
    // const [firstName, setFirstName] = useState('');
    // const [lastName, setLastName] = useState('');

    // const [errorMessage, setErrorMessage] = useState('');
    // const navigate = useNavigate(); // Sử dụng useNavigate hook để điều hướng
  
    // const handleLogin = async (event) => {
    //   event.preventDefault();
    //   try {

    //     // Gửi username và password đến server để xác thực
    //     const response = await axios.post('https://localhost:7190/api/User/AuthenticateUser', {
    //       email: email,
    //       password: password,
    //       firstName:"",
    //       lastName:""
    //     });
    //     if (response.status === 200) {
    //         localStorage.setItem('loggedIn', true);
    //         localStorage.setItem('nameuser', response.data.firstName);
    //         window.location.href = "/home";
    //       }

    //   } catch (error) {
    //     console.error('Lỗi đăng nhập:', error);
    //     // Thiết lập thông báo lỗi cho người dùng
    //     setErrorMessage('Tài khoản hoặc mật khẩu không hợp lệ!!');
    //   }
    // };
  return (
    <>
    <section className="section-conten padding-y" style={{minHeight: '84vh'}}>
  {/* ============================ COMPONENT LOGIN   ================================= */}
  <div className="card mx-auto" style={{maxWidth: 380, marginTop: 100}}>
    <div className="card-body">
      <h4 className="card-title mb-4">Sign in</h4>
      {/* {errorMessage && <div className="alert alert-danger">{errorMessage}</div>} */}
      <form>
      {/* <form onSubmit={handleLogin}> */}
        <a href="#" className="btn btn-facebook btn-block mb-2"> <i className="fab fa-facebook-f" /> &nbsp;  Sign in with Facebook</a>
      <GoogleLogin className="btn btn-google btn-block  mb-4 bg-danger"
          clientId={clientId}
          buttonText="Sign in with Google"
          onSuccess={onSuccess}
          onFailure={onFailure}
          cookiePolicy={'single_host_origin'}
          isSignedIn={true}
        />
        <div className="form-group">
          <input name className="form-control" placeholder="Username" 
                        type="text" />
        </div> {/* form-group// */}
        <div className="form-group">
          <input name className="form-control" placeholder="Password"  
         type="password" />
        </div> {/* form-group// */}
        <div className="form-group">
          <a href="#" className="float-right">Forgot password?</a> 
          <label className="float-left custom-control custom-checkbox"> <input type="checkbox" className="custom-control-input" defaultChecked /> <div className="custom-control-label"> Remember </div> </label>
        </div> {/* form-group form-check .// */}
        <div className="form-group">
          <button type="submit" className="btn btn-primary btn-block">  Log In
          
          </button>
        </div> {/* form-group// */}    
      </form>
    </div> {/* card-body.// */}
  </div> {/* card .// */}
  <p className="text-center mt-4">Don't have account? <Link to={`/register`}>Sign up</Link></p>
  <br /><br />
  {/* ============================ COMPONENT LOGIN  END.// ================================= */}
</section>
</>
  )
}

export default Login