import axios from "axios";
// import { debounce } from "lodash";
import React, { useEffect, useRef, useState } from "react";
import { GoogleLogout } from "react-google-login";
import { Link } from "react-router-dom";
import logo from '../assets/images/logo.svg';
const clientId =
  "349196289553-cbok7vjqcr257sp3qp82vtlnm05704rl.apps.googleusercontent.com";

const fetchProductList = async () => {
  try {
    const response = await axios.get("http://localhost:8080/products");
    return response.data;
  } catch (error) {
    console.error("Lỗi khi lấy danh sách sản phẩm:", error);
    return [];
  }
};

const Header = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userInfo, setUserInfo] = useState(null);

  const [products, setProducts] = useState([]);
  const [carts, setCarts] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [name, setName] = useState("");
  const [suggestions, setSuggestions] = useState([]);
  const debounceTimeoutRef = useRef(null);
  useEffect(() => {
    const loggedIn = localStorage.getItem("loggedIn") === "true";
    const username = localStorage.getItem("nameuser");
    if (username) {
      setName(username);
    }
    // console.log("Is logged in:", loggedIn);
    setIsLoggedIn(loggedIn);
    const fetchData = async () => {
      const productList = await fetchProductList();
      setProducts(productList);
    };
    fetchData();
  }, []);
  const handleLogout = () => {
    localStorage.removeItem("loggedIn");
    setIsLoggedIn(false);
  };
  const handleInputChange = (event) => {
    const value = event.target.value;
    setSearchTerm(value);

    // Clear the previous timeout
    if (debounceTimeoutRef.current) {
      clearTimeout(debounceTimeoutRef.current);
    }

    // Set a new timeout
    debounceTimeoutRef.current = setTimeout(() => {
      const filteredSuggestions = products.filter((product) =>
        product.product_name.toLowerCase().includes(value.toLowerCase())
      );
      setSuggestions(filteredSuggestions);

      if (value === "") {
        setSuggestions([]);
      }
    }, 300); // 300ms delay, you can adjust this value
  };
  // const handleClearSearch = () => {
  //   setSuggestions([]);
  //   setSearchTerm("");
  // };

  useEffect(() => {
    // Lấy thông tin từ Local Storage khi component được mount
    const loggedIn = localStorage.getItem("loggedIn") === "true";

    const storedUserInfo = localStorage.getItem("userInfo");
    if (storedUserInfo) {
      // Chuyển đổi từ chuỗi JSON thành đối tượng JavaScript
      const userInfoObject = JSON.parse(storedUserInfo);
      setUserInfo(userInfoObject);
    }
    setIsLoggedIn(loggedIn);
  }, []);
//   useEffect(() => {
//     const fetchcarts = async () => {
//     const storedUserInfo = localStorage.getItem("userInfo");

// if(isLoggedIn == true)
//   {
//           try {
//         const response = await axios.get('http://localhost:8080/api/cards');
//         setCarts(response.data);

//         // Kiểm tra xem có phần tử nào có google_customer bằng với userInfo.googleId hay không
//         const existingCart = response.data.find(cart => cart.google_customer === storedUserInfo.googleId);

//         if (!existingCart) {
//           // Nếu không có, tạo mới
//           await axios.post('http://localhost:8080/api/cards', {
//             google_customer: storedUserInfo.googleId,
//           });
//         }
//       } catch (error) {
//         console.error('Lỗi khi gọi API:', error);
//       }
//   }
//     };

//     fetchcarts();

//     return () => { };
//   }, [isLoggedIn]);
const onSuccess = async () => {
  const userInfo = JSON.parse(localStorage.getItem("userInfo"));
  const cart = localStorage.getItem("cart");
  const listcart = JSON.parse(cart);

  if (userInfo && cart) {
    try {
      // Check if a card exists for the user
      let response = await axios.get(`http://localhost:8080/api/cards/search?google_customer=${userInfo.googleId}`);
      
      let cardId;
      if (response.status === 200) {
        // Card exists, retrieve card_id
        cardId = response.data.card_id;
      } else if (response.status === 202) {
        // Card does not exist, create a new one
        response = await axios.post('http://localhost:8080/api/cards', {
          googlecustomer: userInfo.googleId,
        });
        cardId = response.data.card_id;
      }


      await axios.delete(`http://localhost:8080/api/CardItems/byCard/${cardId}`);
      // Add product to the card
      for (let item of listcart) {
        await axios.post('http://localhost:8080/api/CardItems', {
          product_id: item.product_id,
          card: cardId,
          quantity: item.quantity,
          regular_price: item.regular_price
        });
      }
    } catch (error) {
      console.error('Error calling API:', error);
    }
  }

  // Clear local storage and update state
  localStorage.removeItem("loggedIn");
  localStorage.removeItem("userInfo");
  localStorage.removeItem("cart");
  setIsLoggedIn(false);
            window.location.href = "/login";
  
};



  const [brand, setBrand] = useState();
  const [category, setCategory] = useState();
  useEffect(() => {
    const fetchBrand = async () => {
      try {
        const [brandReponse, categoryReponse] = await Promise.all([
          axios.get("http://localhost:8080/brands"),
          axios.get("http://localhost:8080/categories"),
        ]);
        setBrand(brandReponse.data);
        setCategory(categoryReponse.data);
      } catch (error) {
        console.error("Lỗi khi gọi API:", error);
      }
    };
    fetchBrand();
    return () => {};
  }, []);
    // console.log('localStorage contents:', {...localStorage});


  return (
    <>
      <header className="section-header">
        <nav className="navbar d-none d-md-flex p-md-0 navbar-expand-sm navbar-light border-bottom">
          <div className="container">
            <button
              className="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarTop4"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon" />
            </button>
            <div className="collapse navbar-collapse" id="navbarTop4">
              <ul className="navbar-nav mr-auto">
                {isLoggedIn ? (
                  <li className="nav-item dropdown">
                    <a
                      href="#"
                      className="nav-link dropdown-toggle"
                      data-toggle="dropdown"
                    >
                      {" "}
                      <img width={30} height={30} src={userInfo.imageUrl} />
                    </a>
                    <ul className="dropdown-menu small">
                      <li>
                        <a className="dropdown-item" href="#">
                          Hello {userInfo.givenName}
                        </a>
                      </li>
                      <li>
                        <a className="dropdown-item" href="#">
                          {" "}
                          <GoogleLogout
                            clientId={clientId}
                            buttonText="Đăng Xuất"
                            onLogoutSuccess={onSuccess}
                          />
                        </a>
                      </li>
                    </ul>
                  </li>
                ) : (
                  <li>
                    <span className="nav-link">
                      Xin chào bạn muốn <Link to={`/login`}> Đăng nhập </Link> or{" "}
                      <Link to={`/register`}> đăng ký </Link>
                    </span>
                  </li>
                )}

                <li>
                  <a href="#" className="nav-link">
                    {" "}
                    Deals{" "}
                  </a>
                </li>
                <li>
                  <a href="#" className="nav-link">
                    {" "}
                    Sell{" "}
                  </a>
                </li>
                <li>
                  <a href="#" className="nav-link">
                    {" "}
                    Help{" "}
                  </a>
                </li>
              </ul>
              <ul className="navbar-nav">
                <li>
                  <a href="#" className="nav-link">
                    {" "}
                    <img
                      src="asset/images/icons/flags/US.png"
                      height={16}
                    />{" "}
                    Ship to{" "}
                  </a>
                </li>
                <li className="nav-item dropdown">
                  <a
                    href="#"
                    className="nav-link dropdown-toggle"
                    data-toggle="dropdown"
                  >
                    {" "}
                    Watchlist{" "}
                  </a>
                  <ul className="dropdown-menu small">
                    <li>
                      <a className="dropdown-item" href="#">
                        First item
                      </a>
                    </li>
                    <li>
                      <a className="dropdown-item" href="#">
                        Second item
                      </a>
                    </li>
                    <li>
                      <a className="dropdown-item" href="#">
                        Third item{" "}
                      </a>
                    </li>
                  </ul>
                </li>
                <li>
                {isLoggedIn ? (
                  
                  <Link
                   to={`/cart`} className="nav-link">
                    {" "}
                    My shop{" "}
                  </Link>
                  ) : (
                    <Link
                    to={`/login`} className="nav-link">
                     {" "}
                     My shop{" "}
                   </Link>
                  )
                }
                </li>
                <li>
                  <a href="#" className="nav-link">
                    {" "}
                    <i className="fa fa-bell" />{" "}
                  </a>
                </li>
                <li>
                  <Link to={`/order`} className="nav-link">
                    {" "}
                    <i className="fa fa-shopping-cart" />{" "}
                  </Link>
                </li>
              </ul>{" "}
              {/* list-inline //  */}
            </div>{" "}
            {/* navbar-collapse .// */}
          </div>{" "}
          {/* container //  */}
        </nav>
        <div className="container">
          <section className="header-main border-bottom">
            <div className="row row-sm">
              <div className="col-6 col-sm col-md col-lg  flex-grow-0">
                <Link to={`/`} className="brand-wrap">
                  <img
                    className="logo"
                   src={logo}
                  />
                </Link>{" "}
                {/* brand-wrap.// */}
              </div>
              <div className="col-6 col-sm col-md col-lg flex-md-grow-0">
                {/* mobile-only */}
                <div className="d-md-none float-right">
                  <a href="#" className="btn btn-light">
                    {" "}
                    <i className="fa fa-bell" />{" "}
                  </a>
                  <a href="#" className="btn btn-light">
                    {" "}
                    <i className="fa fa-user" />{" "}
                  </a>
                  <a href="#" className="btn btn-light">
                    {" "}
                    <i className="fa fa-shopping-cart" /> 2{" "}
                  </a>
                </div>
                {/* mobile-only //end  */}
              </div>{" "}
              {/* col.// */}
              <div className="col-lg-6 col-xl col-md-5 col-sm-12 flex-grow-1">
                <form action="#" className="search-header">
                  <div className="input-group">
                    <input
                      type="text"
                      className="form-control"
                      placeholder="Search"
                      value={searchTerm}
                      onChange={handleInputChange}
                    />
                  </div>
                  <div>
                    {suggestions.length > 0 && (
                      <ul className="navbar-nav flex-wrap">
                        {suggestions.map((product, index) => (
                          <li className="mt-1 mx-2" key={index}>
                            <Link to={`/${product.product_id}`}>{product.product_name}</Link>
                          </li>
                        ))}
                      </ul>
                    )}
                  </div>
                </form>{" "}
                {/* search-wrap .end// */}
              </div>{" "}
              {/* col.// */}
              {/* <div className="col col-lg col-md flex-grow-0">
                <button
                  className="btn btn-block btn-primary"
                  onClick={handleClearSearch}
                  type="submit"
                >
                  {" "}
                  Clear{" "}
                </button>
              </div> */}
            </div>{" "}
            {/* row.// */}
          </section>{" "}
          {/* header-main .// */}
          <nav className="navbar navbar-main navbar-expand pl-0">
            <ul className="navbar-nav flex-wrap">
              <li className="nav-item">
                <Link className="nav-link" to={`/`}>
                  Home
                </Link>
              </li>
              <li className="nav-item dropdown">
                <a
                  className="nav-link dropdown-toggle"
                  data-toggle="dropdown"
                  href="#"
                >
                  {" "}
                  Brand{" "}
                </a>
                <div className="dropdown-menu dropdown-large">
                  <nav className="">
                    <div className="">
                      {brand?.map((item) => (
                        <div className="d-flex justify-content-center">
                          <Link to={`/listpro/${item.id}`}>{item.brand_name}</Link>
                        </div>
                      ))}
                    </div>
                  </nav>{" "}
                  {/*  row end .// */}
                </div>{" "}
                {/*  dropdown-menu dropdown-large end.// */}
              </li>
              <li className="nav-item dropdown">
                <a
                  className="nav-link dropdown-toggle"
                  data-toggle="dropdown"
                  href="#"
                >
                  {" "}
                  Category{" "}
                </a>
                <div className="dropdown-menu dropdown-large">
                  <nav className="">
                    <div className="">
                      {category?.map((item) => (
                        <div className="d-flex justify-content-center">
                          <a href="page-category.html">{item.name}</a>
                        </div>
                      ))}
                    </div>
                  </nav>{" "}
                  {/*  row end .// */}
                </div>{" "}
                {/*  dropdown-menu dropdown-large end.// */}
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">
                  Info
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">
                  Post
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">
                  Support
                </a>
              </li>
            </ul>
          </nav>{" "}
          {/* navbar-main  .// */}
        </div>{" "}
        {/* container.// */}
      </header>{" "}
      {/* section-header.// */}
    </>
  );
};

export default Header;
