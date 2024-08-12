import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import ListPro from "./Home/ListPro";

const Content = () => {
  const [products, setProducts] = useState([]);
  const [gallary, setGallary] = useState([]);
  const [productBrand, setProductBrand] = useState([]);
  const [brand, setBrand] = useState([]);
  const [slider, setSlider] = useState([]);
  

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axios.get("http://localhost:8080/products");
        console.log("====================================");
        console.log(response.data);
        console.log("====================================");
        setProducts(response.data);
        // console.log('====================================');
        // console.log('localStorage contents:', {...localStorage});
        // console.log('====================================');
      } catch (error) {
        console.error("Lỗi khi gọi API:", error);
      }
    };

    fetchProducts();

    return () => {};
  }, []);
  useEffect(() => {
    axios.get("http://localhost:8080/galleries").then((response) => {
      setGallary((data) => {
        return response.data;
      });
    });
  }, []);
  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const [productbrand,brandReponse,sliderReponse] =
          await Promise.all([
            axios.get("http://localhost:8080/productBrands"),
            axios.get("http://localhost:8080/brands"),
            axios.get("http://localhost:8080/slideShows"),
          ]);
        setBrand(brandReponse.data);
        setProductBrand(productbrand.data);
        setSlider(sliderReponse.data);

      } catch (error) {
        console.error("Lỗi khi gọi API:", error);
      }
    };

    fetchProducts();

    return () => { };
  }, []);
  const getProductImages = (productId) => {
    const productImages = gallary.filter((image) => image.productId === productId && image.display_order === 1);
    return productImages.length > 0 ? productImages : []; // Trả về mảng chứa hình ảnh có display_order = 1 hoặc mảng rỗng nếu không có hình ảnh
};
  function formatCurrency(number) {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(number);
}
const addToCart = (product) => {
  try {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    const existingProductIndex = cart.findIndex(item => item.id === product.product_id);

    if (existingProductIndex !== -1) {
      cart[existingProductIndex].quantity++;
    } else {
      product.quantity = 1;
      cart.push(product);
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    console.log("Sản phẩm đã được thêm vào giỏ hàng:", product);
    console.log('====================================');
    console.log('localStorage contents:', {...localStorage});
    console.log('====================================');
  } catch (error) {
    console.error("Lỗi khi thêm sản phẩm vào giỏ hàng:", error);
  }
};

const getCategoryName = (productId) => {
  // Lấy ra productCategory có productId trùng với productId được truyền vào
  const productCategoryItem = productBrand.find(
    (item) => item.productId === productId
  );

  if (productCategoryItem) {
    // Lấy ra categoryName từ category có id tương ứng trong productCategory
    const categoryId = productCategoryItem.brand_id;

    const categoryItem = brand.find((item) => item.id === categoryId);
    if (categoryItem) {
      return categoryItem.brand_name;
    }
  }
  return ""; // Trả về rỗng nếu không tìm thấy
};

  return (
    <div>
      {/* ========================= SECTION MAIN  ========================= */}
      <section className="section-intro padding-y">
        <div className="container">
          {/* ==============  COMPONENT SLIDER  BOOTSTRAP ============  */}
          <div
            id="carousel1_indicator"
            className="slider-home-banner carousel slide"
            data-ride="carousel"
          >
            <ol className="carousel-indicators">
              <li
                data-target="#carousel1_indicator"
                data-slide-to={0}
                className="active"
              />
              <li data-target="#carousel1_indicator" data-slide-to={1} />
            </ol>
            <div className="carousel-inner">
              <div className="carousel-item active">
                <img
                  src="asset/images/banners/slider_Show.png"
                  alt="First slide"
                />
              </div>
              {slider.map(item =>(
    <div className="carousel-item">
    <img
                          src={require(`../../public/asset/images/banners/${item.imageUrl}`)}
                          alt="First slide"
    />
  </div>
              ) )}
            </div>
            <a
              className="carousel-control-prev"
              href="#carousel1_indicator"
              role="button"
              data-slide="prev"
            >
              <span className="carousel-control-prev-icon" aria-hidden="true" />
              <span className="sr-only">Previous</span>
            </a>
            <a
              className="carousel-control-next"
              href="#carousel1_indicator"
              role="button"
              data-slide="next"
            >
              <span className="carousel-control-next-icon" aria-hidden="true" />
              <span className="sr-only">Next</span>
            </a>
          </div>
          {/* ============ COMPONENT SLIDER BOOTSTRAP end.// ===========  .// */}
        </div>{" "}
        {/* container end.// */}
      </section>
      {/* ========================= SECTION MAIN END// ========================= */}
      <div className="container">
        {/* =============== SECTION 1 =============== */}
        <section className="padding-bottom">
          <header className="section-heading mb-4">
            <h3 className="title-section">Sản phẩm đề xuất </h3>
          </header>
          <div className="row">
          {products.map(product => (
            <div className="col-xl-3 col-lg-3 col-md-4 col-6" key={product.product_id}>
              <div className="card card-product-grid">
                <Link to={`/${product.product_id}`} className="img-wrap position-relative">
                {getProductImages(product.product_id).map((image, index) => (
                        <img
                          key={index}
                          src={`${image.image_path}`}
      
                        />
                      ))}
                </Link>
                <div className="overlay position-absolute top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center">
                  <Link to={`/${product.product_id}`} className="img-wrap position-relative"></Link>
                  <div className="overlay-content text-center">
                    <Link to={`/${product.product_id}`} className="img-wrap position-relative"></Link>
                    <Link to={`/cart`} onClick={() => addToCart(product)} className="btn btn-primary">
                      Add To Cart
                    </Link>
                    <Link to={`/${product.product_id}`} className="btn btn-secondary">
                      Quick View
                    </Link>
                  </div>
                </div>
                <figcaption className="info-wrap">
                  <ul className="rating-stars mb-1">
                    <li style={{ width: "80%" }} className="stars-active">
                      <img src="asset/images/icons/stars-active.svg" alt />
                    </li>
                    <li>
                      <img src="asset/images/icons/starts-disable.svg" alt />
                    </li>
                  </ul>
                  <div>
                    <a href="#" className="text-muted">
                    {getCategoryName(product.product_id)}
                    </a>
                    <a href="#" className="title title_name">
                    {product.product_name}
                    </a>
                  </div>
                  <div className="price h5 mt-2">{formatCurrency(product.regular_price)}</div> {/* price.// */}
                </figcaption>
              </div>
            </div>
          )).slice(0,16)}
          </div>
          {/* row.// */}
        </section>
        {/* =============== SECTION 1 END =============== */}
        {/* =============== SECTION BANNER =============== */}

        {/* =============== SECTION BANNER .//END =============== */}
        {/* =============== SECTION 2 =============== */}
        <section className="padding-bottom">

<ListPro />
          {/* row.// */}
        </section>
        {/* =============== SECTION 2 END =============== */}
        {/* =============== SECTION BANNER =============== */}

        {/* =============== SECTION BANNER .//END =============== */}
      </div>
    </div>
  );
};

export default Content;
