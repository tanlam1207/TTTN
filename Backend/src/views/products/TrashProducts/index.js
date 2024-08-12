import { cilClipboard, cilListHighPriority, cilPeople, cilTrash } from '@coreui/icons'
import CIcon from '@coreui/icons-react'
import { CAvatar, CButton, CCol, CFormCheck, CRow, CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow } from '@coreui/react'
import axios from 'axios'
import React, { useEffect, useState } from 'react'

const TrashProducts = () => {
    const [products, setProducts] = useState([])
  const [gallary, setGallary] = useState([])

    useEffect(() => {
        const fetchProducts = async () => {
          try {
            const [productResponse, imgReponse] = await Promise.all([
              axios.get('http://localhost:8080/products'),
              axios.get('http://localhost:8080/galleries'),
            ])
    
            const filteredProducts = productResponse.data.filter((product) => !product.published)
            setProducts(filteredProducts)
            setGallary(imgReponse.data)
          } catch (error) {
            console.error('Lỗi khi gọi API:', error)
          }
        }
    
        fetchProducts()
    
        return () => {}
      }, [])
      const getProductImages = (productId) => {
        const productImages = gallary.filter((image) => image.productId === productId)
        return productImages.length > 0 ? [productImages[0]] : [] // Trả về mảng chứa hình ảnh đầu tiên hoặc mảng rỗng nếu không có hình ảnh
      }
      const handleTrashClick = async (productId) => {
        try {
          const productResponse = await axios.get(`http://localhost:8080/products/${productId}`);
    
          const product = productResponse.data;

          product.published = true;
    
          const updateResponse = await axios.put(
            `http://localhost:8080/products/${productId}`,
            product
          );
    
          window.location.reload();
    
        } catch (error) {
          console.error('Lỗi khi cập nhật status:', error);
        }
      };
      const [checkedProducts, setCheckedProducts] = useState([])

      const handleCheckboxChange = (productId) => {
        setCheckedProducts((prevCheckedProducts) =>
          prevCheckedProducts.includes(productId)
            ? prevCheckedProducts.filter((id) => id !== productId)
            : [...prevCheckedProducts, productId],
        )
      }
      const handleDeleteClick = async (productId) => {
        try {
          for (let productId of checkedProducts) {
          const productResponse = await axios.delete(`http://localhost:8080/products/${productId}`);

          }
          alert("Sản phẩm đã được xóa khỏi thùng rác !");
    
          window.location.reload();
    
        } catch (error) {
          console.error('Lỗi khi cập nhật status:', error);
        }
      };
      const handleDeletesingleClick = async (productId) => {
        try {
  
          const productResponse = await axios.delete(`http://localhost:8080/products/${productId}`);
          
          alert("Sản phẩm đã được xóa khỏi thùng rác !");
    
          window.location.reload();
    
        } catch (error) {
          console.error('Lỗi khi cập nhật status:', error);
        }
      };
  return (
    <div>
       <CRow className='mb-3'>
        <CCol xs={4} className="">
            <CButton color="danger" onClick={handleDeleteClick}>
              <CIcon icon={cilTrash} size="xl"></CIcon>
            </CButton>
          </CCol>
        </CRow>
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
                <CAvatar
                  size="md"
                  src={`${image.image_path}`}
                />
              ))}
            </CTableDataCell>
            <CTableDataCell>
            <div>
              {item.regular_price}
            </div>
            </CTableDataCell>
            <CTableDataCell className="text-center">
              {/* <CIcon size="xl" icon={item.payment.icon} /> */}
              {item.product_id}
            </CTableDataCell>
            <CTableDataCell>
            <CButton  onClick={() => handleTrashClick(item.product_id)} color="info">
           <CIcon icon={cilListHighPriority} size='xl'></CIcon>

           </CButton>
           <CButton className='ms-1' color="danger" onClick={() => handleDeletesingleClick(item.product_id)}>
            <CIcon icon={cilTrash} size='xl'></CIcon>
           </CButton>
      
            </CTableDataCell>
          </CTableRow>
        ))}
      </CTableBody>
    </CTable>
  </div>
  )
}

export default TrashProducts