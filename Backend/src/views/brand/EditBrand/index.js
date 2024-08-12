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



const EditBrand = () => {
  const navigate = useNavigate();
  const { id } = useParams()
  const [brands, setbrands] = useState([])
  const [user, setUser] = useState([])
  const [data, setData] = useState({
    brand_name: "",
    icon: "",
    createdAt: new Date().toISOString(),
    updatedAt: "",
    createdBy: "",
    updatedBy: "",
    active: "",
  })
  useEffect(() => {
    const fetchBrands = async () => {
      try {
        const [BrandResponse,userReponse] = await Promise.all([
          axios.get(`http://localhost:8080/brands/${id}`),
          axios.get("http://localhost:8080/staffAccounts"),
        ])

        setbrands(BrandResponse.data)
        setData(BrandResponse.data)// Khởi tạo dữ liệu chỉnh sửa với dữ liệu ban đầu

        setUser(userReponse.data)

      } catch (error) {
        console.error('Lỗi khi gọi API:', error)
      }
    }

    fetchBrands()

    return () => {}
  }, [])

  const handleImageChange = (e) => {
    setData({
      ...data,
      icon: e.target.files[0].name,
    });
  };
  const getProductImages = (productId) => {
    const productImages = gallary.filter((image) => image.productId === productId)
    return productImages.length > 0 ? [productImages[0]] : [] // Trả về mảng chứa hình ảnh đầu tiên hoặc mảng rỗng nếu không có hình ảnh
  }
  const onSubmit = async (e) => {
    e.preventDefault();
    const filesInput = document.getElementById('formFileMultiple');
    const files = filesInput.files;
    try {
      const response = await axios.put(
        `http://localhost:8080/brands/${id}`,
        data
      );
      if (response.status === 200) {
        navigate('/brands/allBrands')

      } else {
        // Xử lý lỗi
      }
    } catch (error) {
      console.log("====================================");
      console.log(error);
      console.log("====================================");
    } finally {
    }
  };
  return (
    <CCard className="mt-3">
    <CCardBody>
      <CForm onSubmit={onSubmit}>
        <CFormInput className='mb-3' label="Tên thương hiệu" type="text" placeholder="Write here" value={data.brand_name}
              onChange={(e) =>
                setData({ ...data, brand_name: e.target.value })
              } />
        <CRow className='mb-3'>
          <CCol>
          <CFormSelect label="Người cập nhật"
aria-label="Default select example" 
value={data.updatedBy}
              onChange={(e) =>
                setData({ ...data, updatedBy: e.target.value })
              }
options= {user.map((use) => (

{ label: `${use.first_name + use.last_name}`, value: `${use.id}` }

))}
/>
          </CCol>
          <CCol>
          <CFormSelect label="Trạng thái"
aria-label="Default select example" 
value={data.active}
              onChange={(e) =>
                setData({ ...data, active: e.target.value })
              }
              options={[
                'Trạng thái hiệu lực',
                { label: 'Hiện', value: true },
                { label: 'Ẩn', value: false },
              ]}
/>
          </CCol>
        </CRow>

        <CAvatar
                    size="md"
                    src={`src/assets/images/brand/${brands.icon}`}
                  />
    
  
        <CFormInput type="file" id="formFileMultiple" label="Multiple files input example" multiple onChange={handleImageChange} />
        <CButton as="input" className='mt-3' type="submit" color="primary"/>
      </CForm>
    </CCardBody>
  </CCard>
  )
}

export default EditBrand