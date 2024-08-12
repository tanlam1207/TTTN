import React, { useEffect, useRef, useState } from 'react'
import classNames from 'classnames'
import { cilPeople, cilTrash, cilClipboard, cilCheckAlt } from '@coreui/icons'
import {
  CAvatar,
  CButton,
  CCard,
  CCardBody,
  CCol,
  CCollapse,
  CForm,
  CFormCheck,
  CFormInput,
  CFormSelect,
  CRow,
  CTable,
  CTableBody,
  CTableDataCell,
  CTableHead,
  CTableHeaderCell,
  CTableRow,
  CToast,
  CToastBody,
  CToastHeader,
  CToaster,
} from '@coreui/react'
import CIcon from '@coreui/icons-react'
import axios from 'axios'

const AllProducts = () => {
  const [visible, setVisible] = useState(false)
  const [products, setProducts] = useState([])
  const [gallary, setGallary] = useState([])
  const [categories, setCategories] = useState([])
  const [brand, setBrand] = useState([])
  const [tags, setTags] = useState([])
  const [user, setUser] = useState([])
  const [toast, addToast] = useState(0)
  const toaster = useRef()
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
    created_by: '',
    updated_by: '',
    published: true,
    productCategories: [],
    brands: [],
  })
  const [validated, setValidated] = useState(false)

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const [productResponse, imgReponse] = await Promise.all([
          axios.get('http://localhost:8080/products'),
          axios.get('http://localhost:8080/galleries'),
        ])

        const filteredProducts = productResponse.data.filter((product) => product.published)
        setProducts(filteredProducts)
        setGallary(imgReponse.data)
      } catch (error) {
        console.error('Lỗi khi gọi API:', error)
      }
    }

    fetchProducts()

    return () => {}
  }, [])
  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const [categoriesResponse, tagResponse, userReponse, brandReponse] = await Promise.all([
          axios.get('http://localhost:8080/categories'),
          axios.get('http://localhost:8080/tags'),
          axios.get('http://localhost:8080/staffAccounts'),
          axios.get('http://localhost:8080/brands'),
        ])

        setCategories(categoriesResponse.data)
        setTags(tagResponse.data)
        setUser(userReponse.data)
        setBrand(brandReponse.data)
      } catch (error) {
        console.error('Lỗi khi gọi API:', error)
      }
    }

    fetchProducts()

    return () => {}
  }, [])
  const getProductImages = (productId) => {
    const productImages = gallary.filter((image) => image.productId === productId && image.display_order === 1);
    return productImages.length > 0 ? productImages : []; // Trả về mảng chứa hình ảnh có display_order = 1 hoặc mảng rỗng nếu không có hình ảnh
};


  const [checkedProducts, setCheckedProducts] = useState([])

  const handleCheckboxChange = (productId) => {
    setCheckedProducts((prevCheckedProducts) =>
      prevCheckedProducts.includes(productId)
        ? prevCheckedProducts.filter((id) => id !== productId)
        : [...prevCheckedProducts, productId],
    )
  }

  const handleTrashClick = async () => {
    try {
      for (let productId of checkedProducts) {
        const productResponse = await axios.get(`http://localhost:8080/products/${productId}`)
        const product = productResponse.data
        product.published = false

        await axios.put(`http://localhost:8080/products/${productId}`, product)
      }

      alert('Các sản phẩm đã được thêm vào thùng rác!')

      window.location.reload()
    } catch (error) {
      console.error('Lỗi khi cập nhật status:', error)
    }
  }
  const handleTrashSingleClick = async (productId) => {
    try {
      const productResponse = await axios.get(
        `http://localhost:8080/products/${productId}`
      );
      const product = productResponse.data;
      product.published = false;

      const updateResponse = await axios.put(
        `http://localhost:8080/products/${productId}`,
        product
      );

      alert("Sản phẩm đã được thêm vào thùng rác !");

      window.location.reload();
    } catch (error) {
      console.error("Lỗi khi cập nhật status:", error);
    }
  };

  const [selectedTags, setSelectedTags] = useState([])

  const handleTagChange = (event) => {
    const tagId = parseInt(event.target.value)
    const isChecked = event.target.checked

    if (isChecked) {
      setSelectedTags([...selectedTags, tagId])
    } else {
      setSelectedTags(selectedTags.filter((id) => id !== tagId))
    }
  }
  const handleImageChange = (e) => {
    Setdataimg({
      ...dataimg,
      image_path: e.target.files[0].name,
    })
  }
  const exampleToast = (e) => {
    if (validated == true) {
      return (
        <CToast>
          <CToastHeader closeButton>
            <svg
              className="rounded me-2"
              width="20"
              height="20"
              xmlns="http://www.w3.org/2000/svg"
              preserveAspectRatio="xMidYMid slice"
              focusable="false"
              role="img"
            ></svg>
            <CIcon icon={cilCheckAlt} className="text-success" size="xl" />
            <div className="fw-bold me-auto text-success">Success</div>
          </CToastHeader>
          <CToastBody>The product has been created successfully</CToastBody>
        </CToast>
      )
    }
    return ''
  }
  const onSubmit = async (e) => {
    e.preventDefault();
    const form = e.currentTarget;
    if (form.checkValidity() === false) {
        e.stopPropagation();
    }
    setValidated(true);

    const filesInput = document.getElementById('formFileMultiple');
    const files = filesInput.files;
let checkSuccess=false;
    if (validated === true && files.length > 0) {
        try {
            // Gửi dữ liệu sản phẩm
            const response = await axios.post('http://localhost:8080/products', data);
            const productId = response.data.product_id;

            // Tạo các bản ghi product tag tương ứng với các tag được chọn
            for (const tagId of selectedTags) {
                await axios.post('http://localhost:8080/productTags', {
                    productId: productId,
                    tagid: tagId,
                });
            }

            // Tạo FormData và thêm nhiều hình ảnh
            const formData = new FormData();
            for (let i = 0; i < files.length; i++) {
                formData.append('images', files[i]);
            }

            // Tải lên hình ảnh
            const responseimg = await axios.post('http://localhost:8080/Upload/multiple', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });

            // Giả định responseimg.data chứa một mảng đường dẫn hình ảnh đã tải lên
            const imagePaths = responseimg.data;
            // console.log('====================================');
            // console.log("list img", imagePaths);
            // console.log('====================================');

            // Kiểm tra xem imagePaths có phải là mảng không
            if (Array.isArray(imagePaths) && imagePaths.length > 0) {
                // Lưu các bản ghi gallery
                for (let i = 0; i < imagePaths.length; i++) {
                    await axios.post('http://localhost:8080/galleries', {
                        productId: productId,
                        thumbnail: dataimg.thumbnail, 
                        image_path: imagePaths[i],
                        display_order: i + 1, 
                        created_at: new Date().toISOString(),
                        updated_at: new Date().toISOString(),
                    });
                }
checkSuccess=true;
                if (checkSuccess === true) {
                    window.location.reload();
                } else {
                    // Xử lý lỗi nếu response.status không phải là 201
                    console.error("Error saving gallery records");
                }
            } else {
                // Xử lý lỗi nếu responseimg.data không phải là mảng
                console.error("Upload response does not contain an array of image paths");
            }
        } catch (error) {
            console.log('====================================');
            console.log(error);
            console.log('====================================');
        } finally {
            // Bất kỳ công việc dọn dẹp cuối cùng nào
        }
    }
};


  return (
    <div>
      <div>
        <CToaster className="p-3" placement="top-end" push={toast} ref={toaster} />
        <CRow className="justify-content-between mb-1">
          <CCol xs={4}>
            {' '}
            <CButton color="primary" onClick={() => setVisible(!visible)}>
              New
            </CButton>
          </CCol>
          <CCol xs={4} className="text-end me-4">
            <CButton color="danger" onClick={handleTrashClick}>
              <CIcon icon={cilTrash} size="xl"></CIcon>
            </CButton>
          </CCol>
        </CRow>

        <CCollapse visible={visible}>
          <CCard className="mt-3">
            <CCardBody>
              <CForm noValidate validated={validated} onSubmit={onSubmit}>
                <CFormInput
                  required
                  feedbackInvalid="Please provide a valid."
                  className="mb-3"
                  label="Name Product"
                  type="text"
                  placeholder="Write here"
                  value={data.product_name}
                  onChange={(e) => setData({ ...data, product_name: e.target.value })}
                />
                <CFormInput
                  required
                  feedbackInvalid="Please provide a valid."
                  className="mb-3"
                  label="Detail Product"
                  type="text"
                  placeholder="Write here"
                  value={data.product_description}
                  onChange={(e) => setData({ ...data, product_description: e.target.value })}
                />
                <CFormInput
                  required
                  feedbackInvalid="Please provide a valid."
                  className="mb-3"
                  label="Short Decription Product"
                  type="text"
                  placeholder="Write here"
                  value={data.short_description}
                  onChange={(e) => setData({ ...data, short_description: e.target.value })}
                />
                <CRow>
                  <CCol>
                    <CFormInput
                      required
                      feedbackInvalid="Please provide a valid."
                      className="mb-3"
                      label="Price Product"
                      type="number"
                      placeholder="Write here"
                      value={data.regular_price}
                      onChange={(e) => setData({ ...data, regular_price: e.target.value })}
                    />
                  </CCol>
                  <CCol>
                    <CFormInput

                      className="mb-3"
                      label="Price Discount Product"
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
                      required
                      feedbackInvalid="Please provide a valid."
                      className=""
                      label="Sku"
                      type="text"
                      placeholder="Write here"
                      value={data.sku}
                      onChange={(e) => setData({ ...data, sku: e.target.value })}
                    />
                  </CCol>
                  <CCol>
                    <CFormInput
                      required
                      feedbackInvalid="Please provide a valid."
                      className=""
                      label="Quantity"
                      type="number"
                      placeholder="Write here"
                      value={data.quantity}
                      onChange={(e) => setData({ ...data, quantity: e.target.value })}
                    />
                  </CCol>
                  <CCol>
                    <CFormInput
                      required
                      feedbackInvalid="Please provide a valid."
                      className=""
                      label="Weight"
                      type="number"
                      placeholder="Write here"
                      value={data.product_weight}
                      onChange={(e) => setData({ ...data, product_weight: e.target.value })}
                    />
                  </CCol>
                </CRow>
                <CFormInput
                  required
                  feedbackInvalid="Please provide a valid."
                  className="mb-3"
                  label="Note"
                  type="text"
                  placeholder="Write here"
                  value={data.product_note}
                  onChange={(e) => setData({ ...data, product_note: e.target.value })}
                />
                <CRow className="mb-3">
                  <CCol>
                    <CFormSelect
                      label="Create By"
                      aria-label="Default select example"
                      value={data.created_by}
                      onChange={(e) => setData({ ...data, created_by: e.target.value })}
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
                      label="Categories"
                      aria-label="Default select example"
                      value={
                        data.productCategories.length > 0
                          ? data.productCategories[0].category_id
                          : ''
                      }
                      onChange={(e) =>
                        setData({
                          ...data,
                          productCategories: [{ category_id: e.target.value }],
                        })
                      }
                      options={categories.map((cate) => ({
                        label: `${cate.name}`,
                        value: `${cate.categoryId}`,
                      }))}
                    />
                    <CFormSelect
                      label="Brand"
                      aria-label="Default select example"
                      value={data.brands.length > 0 ? data.brands[0].brand_id : ''}
                      onChange={(e) =>
                        setData({
                          ...data,
                          brands: [{ brand_id: e.target.value }],
                        })
                      }
                      options={brand.map((bra) => ({
                        label: `${bra.brand_name}`,
                        value: `${bra.id}`,
                      }))}
                    />
                  </CCol>
                  <CCol>
                    {tags.map((tag) => (
                      <div key={tag.tagId}>
                        <input
                          type="checkbox"
                          id={`tag-${tag.id}`}
                          value={tag.id}
                          onChange={handleTagChange}
                        />
                        <label htmlFor={`tag-${tag.id}`}>{tag.tagName}</label>
                      </div>
                    ))}
                  </CCol>
                </CRow>
                <CFormInput type="file" id="formFileMultiple" label="Multiple files input example" multiple />
                <CButton
                  as="input"
                  className="mt-3"
                  type="submit"
                  color="primary"
                  onClick={() => addToast(exampleToast)}
                />
              </CForm>
            </CCardBody>
          </CCard>
        </CCollapse>
      </div>
      {/* <CButton  color="primary" onClick={() => addToast(exampleToast)}/> */}

      <CTable align="middle" className="mb-0 border" hover responsive>
        <CTableHead className="text-nowrap">
          <CTableRow>
            <CTableHeaderCell className="bg-body-tertiary text-center">
              <CIcon icon={cilPeople} />
            </CTableHeaderCell>
            <CTableHeaderCell className="bg-body-tertiary">Name Products</CTableHeaderCell>
            <CTableHeaderCell className="bg-body-tertiary text-center">Img</CTableHeaderCell>
            <CTableHeaderCell className="bg-body-tertiary">Price</CTableHeaderCell>
            <CTableHeaderCell className="bg-body-tertiary text-center">Id</CTableHeaderCell>
            <CTableHeaderCell className="bg-body-tertiary">Extral</CTableHeaderCell>
          </CTableRow>
        </CTableHead>
        <CTableBody>
          {products.map((item, index) => (
            <CTableRow v-for="item in tableItems" key={index}>
              <CTableDataCell className="text-center">
                <CFormCheck
                  id={`checkbox-${item.product_id}`}
                  value={item.product_id}
                  checked={checkedProducts.includes(item.product_id)}
                  onChange={() => handleCheckboxChange(item.product_id)}
                  aria-label="..."
                />
              </CTableDataCell>
              <CTableDataCell>
                <div>{item.product_name}</div>
              </CTableDataCell>
              <CTableDataCell className="text-center">
                {getProductImages(item.product_id).map((image, index) => (
                  <CAvatar size="md" src={`${image.image_path}`} />
                ))}
              </CTableDataCell>
              <CTableDataCell>
                <div>{item.regular_price}</div>
              </CTableDataCell>
              <CTableDataCell className="text-center">{item.product_id}</CTableDataCell>
              <CTableDataCell>
                <CButton color="danger" onClick={() => handleTrashSingleClick(item.product_id)}>
                  <CIcon icon={cilTrash} size="xl"></CIcon>
                </CButton>
                <CButton
                  className="ms-1"
                  href={`/products#/products/edit-product/${item.product_id}`}
                  color="warning"
                >
                  <CIcon icon={cilClipboard} size="xl"></CIcon>
                </CButton>
              </CTableDataCell>
            </CTableRow>
          ))}
        </CTableBody>
      </CTable>
    </div>
  )
}

export default AllProducts
