import React, { useState } from "react";

const Pagenavigation = () => {
  const [currentPage, setCurrentPage] = useState(1);
  const totalPages = 3; // Số lượng trang cần thay đổi tùy thuộc vào số lượng trang thực tế

  const handlePageClick = (page) => {
    setCurrentPage(page);
    // Tự thực hiện logic fetch sản phẩm tương ứng với trang mới ở đây
    // fetchData(page);
  };

  return (
    <nav className="mb-4" aria-label="Page navigation sample">
      <ul className="pagination">
        <li className={`page-item ${currentPage === 1 ? 'disabled' : ''}`}>
          <a className="page-link" href="#" onClick={() => handlePageClick(currentPage - 1)}>Previous</a>
        </li>
        {Array.from({ length: totalPages }, (_, index) => (
          <li key={index} className={`page-item ${currentPage === index + 1 ? 'active' : ''}`}>
            <a className="page-link" href="#" onClick={() => handlePageClick(index + 1)}>
              {index + 1}
            </a>
          </li>
        ))}
        <li className={`page-item ${currentPage === totalPages ? 'disabled' : ''}`}>
          <a className="page-link" href="#" onClick={() => handlePageClick(currentPage + 1)}>Next</a>
        </li>
      </ul>
    </nav>
  );
};

export default Pagenavigation;
