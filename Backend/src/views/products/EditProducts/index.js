import {
  CAvatar,
  CButton,
  CCard,
  CCardBody,
  CCol,
  CCollapse,
  CForm,
  CFormInput,
  CFormSelect,
  CRow,
} from '@coreui/react'
import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useParams,useNavigate } from 'react-router-dom'

const EditProducts = () => {
  const navigate = useNavigate();

  const [products, setProducts] = useState([])
  var idprocate= ""
  const [gallary, setGallary] = useState([])
  const [categories, setCategories] = useState([])
  const [tags, setTags] = useState([])
  const [user, setUser] = useState([])
  const { id } = useParams()
  const [brand, setBrand] = useState([])

  const [dataimg, Setdataimg] = useState({
    thumbnail: '1',
    image_path: '',
    display_order: 1,
    created_at: new Date().toISOString(),
    updated_at: '',
  })
  const [data, setData] = useState({
    product_name: '',
    sku: '',
    regular_price: '',
    discount_price: '',
    quantity: '',
    short_description: '',
    product_description: '',
    product_weight: '',
    product_note: '',
    created_at: new Date().toISOString(),
    updated_at: '',
    created_by: products.created_by,
    updated_by: '',
    published: true,
    productCategories: [],
    brands:[],
  })
  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const [productResponse, imgReponse] = await Promise.all([
          axios.get(`http://localhost:8080/products/${id}`),
          axios.get(`http://localhost:8080/galleries/by-product/${id}`),
        ]);
        setProducts(productResponse.data);
        setData(productResponse.data); // Khởi tạo dữ liệu chỉnh sửa với dữ liệu ban đầu
        setGallary(imgReponse.data);
        setLoading(false); // Kết thúc quá trình tải dữ liệu
      } catch (error) {
        console.error('Lỗi khi gọi API:', error);
        setLoading(false); // Kết thúc quá trình tải dữ liệu
      }
    };

    fetchProducts();
  }, [id]);
  const getProductImages = (productId) => {
    return gallary.filter((image) => image.productId === productId);
  };
  const getProductImage = (productId) => {
    const productImages = gallary.filter(
      (image) => image.productId === productId
    );
    return productImages.slice(0, 2); // Chỉ lấy hai hình ảnh đầu tiên
  };
 const handleTagChange = (e) => {
    // Xử lý thay đổi trạng thái của checkbox
    const tagId = parseInt(e.target.value);
    const isChecked = e.target.checked;

    // Cập nhật trạng thái checked trong tagpro
    if (isChecked) {
      setTagpro([...tagpro, tagId]); // Thêm tagId vào danh sách nếu được chọn
    } else {
      setTagpro(tagpro.filter((tag) => tag !== tagId)); // Loại bỏ tagId khỏi danh sách nếu bỏ chọn
    }
  };

  const handleImageChange = (e) => {
    Setdataimg({
      ...dataimg,
      image_path: e.target.files[0].name,
    });
  };
  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const [categoriesResponse, tagResponse, userReponse,brandReponse] =
          await Promise.all([
            axios.get("http://localhost:8080/categories"),
            axios.get("http://localhost:8080/tags"),
            axios.get("http://localhost:8080/staffAccounts"),
            axios.get("http://localhost:8080/brands"),
            
          ]);


        setCategories(categoriesResponse.data);
        setTags(tagResponse.data);
        setUser(userReponse.data);
        setBrand(brandReponse.data);

      } catch (error) {
        console.error("Lỗi khi gọi API:", error);
      }
    };

    fetchProducts();

    return () => { };
  }, []);
  const [tagpro, setTagpro] = useState([])
  const [idtagpro, setIdTagpro] = useState([])
  useEffect(() => {
    const fetchTagpro = async () => {
      try {
        const productTagsResponse = await axios.get('http://localhost:8080/productTags')
        const products = productTagsResponse.data
          .filter((item) => item.productId == id)
          .map((item) => item.tagid)
        setTagpro(products)
        const proidtag = productTagsResponse.data
        .filter((item) => item.productId == id)
        .map((item) => item.id)
        setIdTagpro(proidtag)
      } catch (error) {
        console.error('Lỗi khi gọi API:', error)
      }
    }
    fetchTagpro()

    return () => {}
  }, [id])
  const [proBrands, setProBrands] = useState([])
  const [idbrandPro, setIdbrandPro] = useState([])

  useEffect(() => {
    const fetchproductbrand= async () => {
      try {
        const probrandResponse = await axios.get('http://localhost:8080/productBrands')
        const productbras = probrandResponse.data
        .filter((item) => item.productId == id)
        .map((item) => item.brand_id)
        setProBrands(productbras)
      const proidtag = probrandResponse.data
      .filter((item) => item.productId == id)
      .map((item) => item.id)
      setIdbrandPro(proidtag)
    } catch (error) {
      console.error('Lỗi khi gọi API:', error)
    }
    }
    fetchproductbrand()

    return () => {}
  }, [id])
console.log('====================================');
console.log("branpro :",idbrandPro,"brpro :",proBrands);
console.log('====================================');
  const [gallerypro, setGallerypro] = useState([])
  useEffect(() => {
    const fetchgallerypro = async () => {
      try {
        const galleryResponse = await axios.get('http://localhost:8080/galleries')
        const gallery = galleryResponse.data
          .filter((item) => item.productId == id)
          .map((item) => item.id)
          setGallerypro(gallery[0])
      } catch (error) {
        console.error('Lỗi khi gọi API:', error)
      }
    }
    fetchgallerypro()

    return () => {}
  }, [id])
const updateProductTags = async (productId, idtagpro, tagpro) => {
  try {
    const updateRequests = idtagpro.map((idtagproduct, index) => {
      const tagId = tagpro[index]; // Lấy tagId tương ứng với idtagpro

      return axios.put(`http://localhost:8080/productTags/${idtagproduct}`, {
        productId: productId,
        tagid: tagId,
      });
    });

    await Promise.all(updateRequests);

    console.log('Đã cập nhật thành công các productTags');
  } catch (error) {
    console.error('Lỗi khi cập nhật productTags:', error);
  }
};

  const onSubmit = async (e) => {
    e.preventDefault()
    const filesInput = document.getElementById('formFileMultiple')
    const files = filesInput.files
    try {
      const response = await axios.put(`http://localhost:8080/products/${id}`, data )
      const productId = response.data.product_id

      await updateProductTags(productId, idtagpro, tagpro);

      await axios.put(`http://localhost:8080/galleries/${gallerypro}`, {
        thumbnail: dataimg.thumbnail, // Cần xử lý để tạo thumbnail
        image_path: dataimg.image_path, // Tên của hình ảnh
        display_order: dataimg.display_order,
        created_at: dataimg.created_at,
        updated_at: dataimg.updated_at,
      })

      if (response.status === 200) {
        navigate('/products/allProducts')
      } else {
        // Xử lý lỗi
      }
    } catch (error) {
      console.log('====================================')
      console.log(error)
      console.log('====================================')
    } finally {
    }
  }
// Kiểm tra xem productCategories có tồn tại và không phải là undefined
if (products.productCategories && products.productCategories.length > 0) {
  // Truy cập vào phần tử đầu tiên trong mảng productCategories của products
  const firstProductCategory = products.productCategories[0];
  idprocate=firstProductCategory.id
  // Kiểm tra xem firstProductCategory có tồn tại và không phải là undefined
  if (firstProductCategory) {
      // Truy cập thuộc tính 'id' của đối tượng ProductCategories và in ra
  } else {
      console.error("Không tìm thấy đối tượng ProductCategories hoặc firstProductCategory là undefined.");
  }
} else {
  console.error("Không tìm thấy mảng productCategories trong đối tượng products hoặc là products.productCategories là undefined.");
}
console.log("user",user.id);
console.log("productsdata",products)
  return (
    <div>
      <CCard className="mt-3">
        <CCardBody>
          <CForm onSubmit={onSubmit}>
            {/* <CFormInput
              className="mb-3"

              type="text"
              placeholder="Write here"
              value={data.product_name}
              onChange={(e) => setProducts({ ...data, product_name: e.target.value })}
            /> */}
             <CFormInput
              className="mb-3"
              label={`Name Product :`}
              type="text"
              placeholder="Write here"
              value={data.product_name}
              onChange={(e) => setData({ ...data, product_name: e.target.value })}
            />
            <CFormInput
              className="mb-3"
              label={`Detail Product:`}
              type="text"
              placeholder="Write here"
              value={data.product_description}
              onChange={(e) => setData({ ...data, product_description: e.target.value })}
            />
            <CFormInput
              className="mb-3"
              label={`Short Decription Product:`}
              type="text"
              placeholder="Write here"
              value={data.short_description}
              onChange={(e) => setData({ ...data, short_description: e.target.value })}
            />
            <CRow>
              <CCol>
                <CFormInput
                  className="mb-3"
                  label={`Price Product: `}
                  type="number"
                  placeholder="Write here"
                  value={data.regular_price}
                  onChange={(e) => setData({ ...data, regular_price: e.target.value })}
                />
              </CCol>
              <CCol>
                <CFormInput
                  className="mb-3"
                  label={`Price discount Product: `}
                  type="number"
                  placeholder="Write here"
                  value={data.discount_price}
                  onChange={(e) => setData({ ...data, discount_price: e.target.value })}
                />
              </CCol>
            </CRow>
            <CRow>
              <CCol>
                <CFormInput
                  className=""
                  label={`Sku: `}
                  type="text"
                  placeholder="Write here"
                  value={data.sku}
                  onChange={(e) => setData({ ...data, sku: e.target.value })}
                />
              </CCol>
              <CCol>
                <CFormInput
                  className=""
                  label={`Quantity:`}
                  type="number"
                  placeholder="Write here"
                  value={data.quantity}
                  onChange={(e) => setData({ ...data, quantity: e.target.value })}
                />
              </CCol>
              <CCol>
                <CFormInput
                  className=""
                  label={`Weight Product:`}
                  type="number"
                  placeholder="Write here"
                  value={data.product_weight}
                  onChange={(e) => setData({ ...data, product_weight: e.target.value })}
                />
              </CCol>
            </CRow>
            <CFormInput
              className="mb-3"
              label={`Note: `}
              type="text"
              placeholder="Write here"
              value={data.product_note}
              onChange={(e) => setData({ ...data, product_note: e.target.value })}
            />
            <CRow className="mb-3">
              <CCol>
                <CFormSelect
                  label={`Update By :`}
                  aria-label="Default select example"
                  value={data.created_by}
                  onChange={(e) => setData({ ...data, updated_by: e.target.value })}
                  options={user.map((use) => ({
                    label: `${use.first_name + use.last_name}`,
                    value: `${use.id}`,
                  }))}

                />
              </CCol>
            </CRow>
            <CRow className="mb-3">
              <CCol>
                <CFormSelect
                  label={`Categories:`}
                  aria-label="Default select example"
                  value={
                    data.productCategories.length > 0 ? data.productCategories[0].category_id : ''
                  }
                  onChange={(e) =>
                    setData({
                      ...data,
                      productCategories: [{ category_id: e.target.value ,id :idprocate,productId:id }],
                    })
                  }
                  options={categories.map((cate) => ({
                    label: `${cate.name}`,
                    value: `${cate.categoryId}`,
                  }))}
                />
                <CFormSelect label="Brand"
  aria-label="Default select example"
  value={data.brands.length > 0 ? data.brands[0].brand_id : ''}
  onChange={(e) =>
    setData({
      ...data,
      brands: [{ 
        id:idbrandPro[0],
        brand_id: e.target.value,
        productId:id
      }],
    })
  }
  options= {brand.map((bra) => (

     { label: `${bra.brand_name}`, value: `${bra.id}` }
    
  ))}
/>
                          {getProductImages(products.product_id).map((image, index) => (
                  <CAvatar
                    size="xl"
                    src={image.image_path}
                  />
                ))}
              </CCol>
              <CCol>
                {tags.map((tag) => (
                  <div key={tag.tagId}>
                    <input
                      type="checkbox"
                      id={`tag-${tag.id}`}
                      value={tag.id}
                      checked={tagpro.includes(tag.id)}
                      onChange={handleTagChange}
                    />
                    <label htmlFor={`tag-${tag.id}`}>{tag.tagName}</label>
                  </div>
                ))}
              </CCol>
            </CRow>
            <CFormInput
              type="file"
              id="formFileMultiple"
              label="Multiple files input example"
              multiple
              onChange={handleImageChange}
            />
            <CButton as="input" className="mt-3" type="submit" color="primary" />
          </CForm>
        </CCardBody>
      </CCard>
    </div>
  )
}

export default EditProducts
