import React, { useEffect, useState } from 'react'
import classNames from 'classnames'
import { cilPeople, cilTrash, cilClipboard } from '@coreui/icons'
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
  CImage,
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

const Slider = () => {
  const [visible, setVisible] = useState(false)
  const [slider, setSlider] = useState([])
  const [user, setUser] = useState([])
  const [data, setData] = useState({
    destinationUrl: '',
    createdAt: new Date().toISOString(),
    updatedAt: '',
    createdBy: '',
    updatedBy: '',
    imageUrl: '',
    clicks: '',
  })
  useEffect(() => {
    const fetchcategory = async () => {
      try {
        const [SlideResponse, userReponse] = await Promise.all([
          axios.get('http://localhost:8080/slideShows'),
          axios.get('http://localhost:8080/staffAccounts'),
        ])

        setSlider(SlideResponse.data)
        setUser(userReponse.data)
      } catch (error) {
        console.error('Lỗi khi gọi API:', error)
      }
    }

    fetchcategory()

    return () => {}
  }, [])

  const handleDeleteClick = async (productId) => {
    try {
      const productResponse = await axios.delete(`http://localhost:8080/slideShows/${productId}`);
      alert("Sản phẩm đã được xóa khỏi thùng rác !");

      window.location.reload();

    } catch (error) {
      console.error('Lỗi khi cập nhật status:', error);
    }
  };

  const handleImageChange = (e) => {
    setData({
      ...data,
      imageUrl: e.target.files[0].name,
    })
  }

  const onSubmit = async (e) => {
    e.preventDefault()
    const filesInput = document.getElementById('formFileMultiple')
    const files = filesInput.files
    try {
      const response = await axios.post('http://localhost:8080/slideShows', data)
      if (response.status === 201) {
        window.location.reload()
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
                <CFormInput
                  className="mb-3"
                  label="Địa chỉ đích "
                  type="text"
                  placeholder="Write here"
                  value={data.destinationUrl}
                  onChange={(e) => setData({ ...data, destinationUrl: e.target.value })}
                />
                <CRow className="mb-3">
                  <CCol>
                    <CFormSelect
                      label="Người tạo"
                      aria-label="Default select example"
                      value={data.createdBy}
                      onChange={(e) => setData({ ...data, createdBy: e.target.value })}
                      options={user.map((use) => ({
                        label: `${use.first_name + use.last_name}`,
                        value: `${use.id}`,
                      }))}
                    />
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
        </CCollapse>
      </div>
      <CTable align="middle" className="mb-0 border" hover responsive>
        <CTableHead className="text-nowrap">
          <CTableRow>
            <CTableHeaderCell className="bg-body-tertiary text-center">
              <CIcon icon={cilPeople} />
            </CTableHeaderCell>
            <CTableHeaderCell className="bg-body-tertiary">Địa chỉ đích</CTableHeaderCell>
            <CTableHeaderCell className="bg-body-tertiary text-center">hình ảnh</CTableHeaderCell>
            <CTableHeaderCell className="bg-body-tertiary text-center">Id</CTableHeaderCell>
            <CTableHeaderCell className="bg-body-tertiary text-center">Ngày tạo</CTableHeaderCell>
            <CTableHeaderCell className="bg-body-tertiary">Chức năng</CTableHeaderCell>
          </CTableRow>
        </CTableHead>
        <CTableBody>
          {slider.map((item, index) => (
            <CTableRow v-for="item in tableItems" key={index}>
              <CTableDataCell className="text-center"></CTableDataCell>
              <CTableDataCell>
                <a href={item.destinationUrl}>{item.destinationUrl}
</a>
              </CTableDataCell>
              <CTableDataCell className="text-center">
                {/* <CAvatar
                      size="xl"
                      src={`src/assets/images/banner/banners/${item.imageUrl}`}
                    /> */}
                <CImage
                  rounded
                  thumbnail
                  s
                  src={`src/assets/images/banner/banners/${item.imageUrl}`}
                  width={200}
                  height={200}
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
                <CButton color="danger" onClick={() => handleDeleteClick(item.id)}>
                  <CIcon icon={cilTrash} size="xl"></CIcon>
                </CButton>
                <CButton
                  className="ms-1"
                  href={`/category#/category/edit-category/${item.id}`}
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

export default Slider
