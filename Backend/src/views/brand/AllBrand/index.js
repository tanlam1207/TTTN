import React, { useEffect, useState } from 'react'
import classNames from 'classnames'
import {
  cibCcAmex,
  cibCcApplePay,
  cibCcMastercard,
  cibCcPaypal,
  cibCcStripe,
  cibCcVisa,
  cibGoogle,
  cibFacebook,
  cibLinkedin,
  cifBr,
  cifEs,
  cifFr,
  cifIn,
  cifPl,
  cifUs,
  cibTwitter,
  cilCloudDownload,
  cilPeople,
  cilUser,
  cilUserFemale,
  cilTrash,
  cilCheckCircle,
  cilClipboard,
} from '@coreui/icons'
import {
  CAlert,
  CAvatar,
  CButton,
  CButtonGroup,
  CCard,
  CCardBody,
  CCardFooter,
  CCardHeader,
  CCol,
  CCollapse,
  CForm,
  CFormCheck,
  CFormInput,
  CFormSelect,
  CProgress,
  CRow,
  CTable,
  CTableBody,
  CTableDataCell,
  CTableHead,
  CTableHeaderCell,
  CTableRow,
} from '@coreui/react'
import CIcon from '@coreui/icons-react'
import axios from 'axios'
const AllBrand = () => {
  const [visible, setVisible] = useState(false)
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
          axios.get('http://localhost:8080/brands'),
          axios.get("http://localhost:8080/staffAccounts"),

          
        ])

        const filteredBrands = BrandResponse.data.filter((product) => product.active)
        setbrands(filteredBrands)
        setUser(userReponse.data);

      } catch (error) {
        console.error('Lỗi khi gọi API:', error)
      }
    }

    fetchBrands()

    return () => {}
  }, [])

 const handleTrashClick = async (productId) => {
    try {
      const productResponse = await axios.get(
        `http://localhost:8080/brands/${productId}`
      );
      const product = productResponse.data;
      product.active = false;

      const updateResponse = await axios.put(
        `http://localhost:8080/brands/${productId}`,
        product
      );

      alert("Sản phẩm đã được thêm vào thùng rác !");

      window.location.reload();
    } catch (error) {
      console.error("Lỗi khi cập nhật status:", error);
    }
  };

  const handleImageChange = (e) => {
    setData({
      ...data,
      icon: e.target.files[0].name,
    });
  };
  
  const onSubmit = async (e) => {
    e.preventDefault();
    const filesInput = document.getElementById('formFileMultiple');
    const files = filesInput.files;
    try {
      const response = await axios.post(
        "http://localhost:8080/brands",
        data
      );
      if (response.status === 201) {
      window.location.reload();
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
    <div>
      <div>
    <CButton color="primary" onClick={() => setVisible(!visible)}>
      New
    </CButton>
    <CCollapse visible={visible}>
      <CCard className="mt-3">
        <CCardBody>
          <CForm onSubmit={onSubmit}>
            <CFormInput className='mb-3' label="Tên thương hiệu" type="text" placeholder="Write here" value={data.brand_name}
                  onChange={(e) =>
                    setData({ ...data, brand_name: e.target.value })
                  } />
            <CRow className='mb-3'>
              <CCol>
              <CFormSelect label="Người tạo"
  aria-label="Default select example" 
  value={data.createdBy}
                  onChange={(e) =>
                    setData({ ...data, createdBy: e.target.value })
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
            <CFormInput type="file" id="formFileMultiple" label="Multiple files input example" multiple onChange={handleImageChange} />
            <CButton as="input" className='mt-3' type="submit" color="primary"/>
          </CForm>
        </CCardBody>
      </CCard>
    </CCollapse>
      </div>
      <CTable align="middle" className="mb-0 border" hover responsive>
        <CTableHead className="text-nowrap">
          <CTableRow>
            <CTableHeaderCell className="bg-body-tertiary text-center">
              <CIcon icon={cilPeople} />
            </CTableHeaderCell>
            <CTableHeaderCell className="bg-body-tertiary">Tên thương hiệu</CTableHeaderCell>
            <CTableHeaderCell className="bg-body-tertiary text-center">Icon</CTableHeaderCell>
            <CTableHeaderCell className="bg-body-tertiary text-center">Id</CTableHeaderCell>
            <CTableHeaderCell className="bg-body-tertiary text-center">Ngày tạo</CTableHeaderCell>
            <CTableHeaderCell className="bg-body-tertiary">Chức năng</CTableHeaderCell>
          </CTableRow>
        </CTableHead>
        <CTableBody>
          {brands.map((item, index) => (
            <CTableRow v-for="item in tableItems" key={index}>
              <CTableDataCell className="text-center">
              
              </CTableDataCell>
              <CTableDataCell>
                <div>{item.brand_name}</div>
              
              </CTableDataCell>
              <CTableDataCell className="text-center">
                  <CAvatar
                    size="md"
                    src={`src/assets/images/brand/${item.icon}`}
                  />
              </CTableDataCell>
              <CTableDataCell className="text-center">
                {/* <CIcon size="xl" icon={item.payment.icon} /> */}
                {item.id}
              </CTableDataCell>
              <CTableDataCell className="text-center">
                {/* <CIcon size="xl" icon={item.payment.icon} /> */}
                {item.createdAt}
              </CTableDataCell>
              <CTableDataCell>
             <CButton color="danger" onClick={() => handleTrashClick(item.id)}>
              <CIcon icon={cilTrash} size='xl'></CIcon>
             </CButton>
             <CButton className='ms-1' href={`/brands#/brands/edit-brand/${item.id}`} color="warning">
             <CIcon icon={cilClipboard} size='xl'></CIcon>

             </CButton>
              </CTableDataCell>
            </CTableRow>
          ))}
        </CTableBody>
      </CTable>
    </div>
  )
}


export default AllBrand