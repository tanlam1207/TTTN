import axios from "axios";
// import { debounce } from "lodash";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
const clientId =
  "349196289553-c0h9skjqp58vdb1nuhouopnjg7geag75.apps.googleusercontent.com";

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
  const [products, setProducts] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [suggestions, setSuggestions] = useState([]);
  useEffect(() => {
    const loggedIn = localStorage.getItem("loggedIn") === "true";
    console.log("Is logged in:", loggedIn);
    setIsLoggedIn(loggedIn);
    const fetchData = async () => {
      const productList = await fetchProductList();
      setProducts(productList);
    };
    fetchData();
  }, []);
  const handleLogout = () => {
    const userInfo = JSON.parse(localStorage.getItem('userInfo'));
    const cart = localStorage.getItem('cart');
  
    if (userInfo && cart) {
      localStorage.setItem(`cart_${userInfo.id}`, cart);
    }
  
    localStorage.removeItem('loggedIn');
    localStorage.removeItem('userInfo');
    localStorage.removeItem('cart');
    setIsLoggedIn(false);
  };
  
  const handleInputChange = (event) => {
    const value = event.target.value;
    setSearchTerm(value);
    const filteredSuggestions = products.filter((product) =>
      product.product_name.toLowerCase().includes(value.toLowerCase())
    );
    setSuggestions(filteredSuggestions);
  };
  const handleClearSearch = () => {
    setSuggestions([]);
    setSearchTerm("");
  };
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
                <li>
                  <span className="nav-link">
                    Hi, <Link to={`/`}> Sign in </Link> or{" "}
                    <Link to={`/register`}> Register </Link>
                  </span>
                </li>
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
                  <Link to={`/cart`} className="nav-link">
                    {" "}
                    My shop{" "}
                  </Link>
                </li>
                <li>
                  <a href="#" className="nav-link">
                    {" "}
                    <i className="fa fa-bell" />{" "}
                  </a>
                </li>
                <li>
                  <a href="#" className="nav-link">
                    {" "}
                    <i className="fa fa-shopping-cart" />{" "}
                  </a>
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
                <Link to={`/home`} className="brand-wrap">
                  <img className="logo" src="asset/images/logo.svg" />
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
                <div className="category-wrap d-none dropdown d-md-inline-block">
                  <button
                    type="button"
                    className="btn btn-light dropdown-toggle"
                    data-toggle="dropdown"
                  >
                    {" "}
                    Shop by
                  </button>
                  <div className="dropdown-menu">
                    <a className="dropdown-item" href="#">
                      Machinery / Mechanical Parts / Tools{" "}
                    </a>
                    <a className="dropdown-item" href="#">
                      Consumer Electronics / Home Appliances{" "}
                    </a>
                    <a className="dropdown-item" href="#">
                      Auto / Transportation
                    </a>
                    <a className="dropdown-item" href="#">
                      Apparel / Textiles / Timepieces{" "}
                    </a>
                    <a className="dropdown-item" href="#">
                      Home &amp; Garden / Construction / Lights{" "}
                    </a>
                    <a className="dropdown-item" href="#">
                      Beauty &amp; Personal Care / Health{" "}
                    </a>
                  </div>
                </div>{" "}
                {/* category-wrap.// */}
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
                            <Link to={`/${product.product_id}`}>
                              {product.product_name}
                            </Link>
                          </li>
                        ))}
                      </ul>
                    )}
                  </div>
                </form>{" "}
                {/* search-wrap .end// */}
              </div>{" "}
              {/* col.// */}
              <div className="col col-lg col-md flex-grow-0">
                <button
                  className="btn btn-block btn-primary"
                  onClick={handleClearSearch}
                  type="submit"
                >
                  {" "}
                  Clear{" "}
                </button>
              </div>
              <div className="col col-lg col-md flex-grow-0">
                <button className="btn btn-block btn-light" type="submit">
                  {" "}
                  Advanced{" "}
                </button>
              </div>
            </div>{" "}
            {/* row.// */}
          </section>{" "}
          {/* header-main .// */}
          <nav className="navbar navbar-main navbar-expand pl-0">
            <ul className="navbar-nav flex-wrap">
              <li className="nav-item">
                <a className="nav-link" href="#">
                  Home
                </a>
              </li>
              <li className="nav-item dropdown">
                <a
                  className="nav-link dropdown-toggle"
                  data-toggle="dropdown"
                  href="#"
                >
                  {" "}
                  Demo pages{" "}
                </a>
                <div className="dropdown-menu dropdown-large">
                  <nav className="row">
                    <div className="col-6">
                      <a href="page-index-1.html">Home page 1</a>
                      <a href="page-index-2.html">Home page 2</a>
                      <a href="page-category.html">All category</a>
                      <a href="page-listing-large.html">Listing list</a>
                      <a href="page-listing-grid.html">Listing grid</a>
                      <a href="page-shopping-cart.html">Shopping cart</a>
                      <a href="page-detail-product.html">Product detail</a>
                      <a href="page-content.html">Page content</a>
                      <a href="page-user-login.html">Page login</a>
                      <a href="page-user-register.html">Page register</a>
                    </div>
                    <div className="col-6">
                      <a href="page-profile-main.html">Profile main</a>
                      <a href="page-profile-orders.html">Profile orders</a>
                      <a href="page-profile-seller.html">Profile seller</a>
                      <a href="page-profile-wishlist.html">Profile wishlist</a>
                      <a href="page-profile-setting.html">Profile setting</a>
                      <a href="page-profile-address.html">Profile address</a>
                      <a href="page-components.html" target="_blank">
                        More components
                      </a>
                    </div>
                  </nav>{" "}
                  {/*  row end .// */}
                </div>{" "}
                {/*  dropdown-menu dropdown-large end.// */}
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">
                  Category
                </a>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to={`/listpro`}>
                  Brand
                </Link>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">
                  Beauty
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">
                  Motors
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">
                  Sports
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">
                  Gardening
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">
                  Deals
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">
                  Under $10
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
