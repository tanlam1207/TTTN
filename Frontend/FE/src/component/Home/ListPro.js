import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';

const ListPro = () => {
  const [productTags, setProductTags] = useState([]);
  const [tagbest, setTagbest] = useState([]);
  const [tagnew, setTagnew] = useState([]);
  const [product, setProduct] = useState([]);
  const [productBrand, setProductBrand] = useState([]);
  const [brand, setBrand] = useState([]);
  const [gallary, setGallary] = useState([]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const [productbrand,brandReponse,productReponse,galleryReponse,productTagReponse] =
          await Promise.all([
            axios.get("http://localhost:8080/productBrands"),
            axios.get("http://localhost:8080/brands"),
            axios.get("http://localhost:8080/products"),
            axios.get("http://localhost:8080/galleries"),
            axios.get("http://localhost:8080/productTags"),
          ]);
        setBrand(brandReponse.data);
        setProductBrand(productbrand.data);
        setProduct(productReponse.data);
        setGallary(galleryReponse.data);
        setProductTags(productTagReponse.data)
      } catch (error) {
        console.error("Lỗi khi gọi API:", error);
      }
    };

    fetchProducts();

    return () => { };
  }, []);
useEffect(() => {
    axios
      .get("http://localhost:8080/tags")
      .then((response) => {
        // Lọc ra các tag có name là "New"
        const tagProductsbest = response.data.filter(
          (tag) => tag.tagName === "BEST SELLERS"
        );
        const tagProductsnew = response.data.filter(
          (tag) => tag.tagName === "New Products"
        );
        setTagnew(tagProductsnew);
        setTagbest(tagProductsbest);
      })
      .catch((error) => {
        console.error("Error fetching tag data:", error);
      });
  }, []);

  const getbestProduct = () => {
    const bestproduct = productTags
      .filter((productTag) =>
      tagbest.some((tag) => tag.id === productTag.tagid)
      )
      .map((productTag) => productTag.productId);
  
    return product.filter((item) => bestproduct.includes(item.product_id));
  };
  const getNewProduct = () => {
    const newNewProductIds = productTags
      .filter((productTag) =>
      tagnew.some((tag) => tag.id === productTag.tagid)
      )
      .map((productTag) => productTag.productId);
  
    return product.filter((item) => newNewProductIds.includes(item.product_id));
  };
  function formatCurrency(number) {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(number);
}
const getProductImages = (productId) => {
  const productImages = gallary.filter((image) => image.productId === productId && image.display_order === 1);
  return productImages.length > 0 ? productImages : []; // Trả về mảng chứa hình ảnh có display_order = 1 hoặc mảng rỗng nếu không có hình ảnh
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
              <header className="section-heading mb-4">
            <h3 className="title-section">Sản phẩm mới</h3>
          </header>
     <div className="row row-sm">
        {
                      getbestProduct()
                      .map((item, index) => (
                        <div className="col-xl-2 col-lg-3 col-md-4 col-6">
                        <Link to={`/${item.product_id}`} className="card card-sm card-product-grid">
                          <a href="#" className="img-wrap">
                            <b className="badge badge-danger mr-1">10% OFF</b>
                            {getProductImages(item.product_id).map((image, index) => (
                        <img
                          key={index}
                          src={`${image.image_path}`}
      
                        />
                        
                      ))}
                          
                          </a>
                          <figcaption className="info-wrap">
                          <a href="#" className="text-muted">
                    {getCategoryName(item.product_id)}
                    </a>
                            <a href="#" className="title title_name">
                             {item.product_name}
                            </a>
                            <div className="price-wrap">
                              <span className="price">{formatCurrency(item.regular_price)}</span>
                              {/* <del className="price-old">$90</del> */}
                            </div>
                            {/* price-wrap.// */}
                          </figcaption>
                        </Link>
                      </div>
                      )).slice(0, 6)
        }


  </div>
          <section className="padding-bottom">
          <article className="box d-flex flex-wrap align-items-center p-5 bg-secondary">
            <div className="text-white mr-auto">
              <h3>Bạn đang tìm kiếm một chiếc điện thoại giá rẻ ?</h3>
              <p> Các dòng điện thoại phù hợp với túi tiền của bạn.  </p>
            </div>
            <div className="mt-3 mt-md-0">
              <Link to={`/listpro`}  className="btn btn-outline-light">
                Xem thêm
              </Link>
            </div>
          </article>
        </section>

        <header className="section-heading mb-4">
            <h3 className="title-section">Sản phẩm bán chạy</h3>
          </header>
        <div className="row row-sm">
        {
                      getNewProduct()
                      .map((item, index) => (
                        <div className="col-xl-2 col-lg-3 col-md-4 col-6">
                        <Link to={`/${item.product_id}`} className="card card-sm card-product-grid">
                          <a href="#" className="img-wrap">
                            <b className="badge badge-danger mr-1">10% OFF</b>
                            {getProductImages(item.product_id).map((image, index) => (
                        <img
                          key={index}
                          src={`${image.image_path}`}
      
                        />
                        
                      ))}
                          
                          </a>
                          <figcaption className="info-wrap">
                          <a href="#" className="text-muted">
                    {getCategoryName(item.product_id)}
                    </a>
                            <a href="#" className="title title_name">
                             {item.product_name}
                            </a>
                            <div className="price-wrap">
                              <span className="price">{formatCurrency(item.regular_price)}</span>
                              {/* <del className="price-old">$90</del> */}
                            </div>
                            {/* price-wrap.// */}
                          </figcaption>
                        </Link>
                      </div>
                      )).slice(0, 6)
        }


  </div>
   </div>
  )
}

export default ListPro