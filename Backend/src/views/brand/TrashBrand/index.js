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
  cilListHighPriority,
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
const TrashBrand = () => {
  const [brands, setbrands] = useState([])
  useEffect(() => {
    const fetchBrands = async () => {
      try {
        const [BrandResponse] = await Promise.all([
          axios.get('http://localhost:8080/brands'),

          
        ])

        const filteredBrands = BrandResponse.data.filter((product) => !product.active)
        setbrands(filteredBrands)

      } catch (error) {
        console.error('Lỗi khi gọi API:', error)
      }
    }

    fetchBrands()

    return () => {}
  }, [])

  const handleTrashClick = async (productId) => {
    try {
      const productResponse = await axios.get(`http://localhost:8080/brands/${productId}`);

      const product = productResponse.data;

      product.active = true;

      const updateResponse = await axios.put(
        `http://localhost:8080/brands/${productId}`,
        product
      );

      window.location.reload();

    } catch (error) {
      console.error('Lỗi khi cập nhật status:', error);
    }
  };
  const handleDeleteClick = async (productId) => {
    try {
      const productResponse = await axios.delete(`http://localhost:8080/brands/${productId}`);
      alert("Sản phẩm đã được xóa khỏi thùng rác !");

      window.location.reload();

    } catch (error) {
      console.error('Lỗi khi cập nhật status:', error);
    }
  };
  return (
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
            <CButton  onClick={() => handleTrashClick(item.id)} color="info">
           <CIcon icon={cilListHighPriority} size='xl'></CIcon>

           </CButton>
           <CButton className='ms-1' color="danger" onClick={() => handleDeleteClick(item.id)}>
            <CIcon icon={cilTrash} size='xl'></CIcon>
           </CButton>
      
            </CTableDataCell>
        </CTableRow>
      ))}
    </CTableBody>
  </CTable>
  )
}

export default TrashBrand