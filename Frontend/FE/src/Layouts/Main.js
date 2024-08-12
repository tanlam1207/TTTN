import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Home from './Home'
import DetailProduct from './DetailProduct'
import LoginDisplay from './LoginDisplay'
import RegisterDisplay from './RegisterDisplay'
import { DisplayCart } from './DisplayCart'
import DisplayListPro from './DisplayListPro'
import Payment from './Payment'
import Order from './Order'

const Main = () => {
  return (
    <main>
      <Routes>
        <Route path="/login" element={<LoginDisplay />} />
        <Route path="/" element={<Home />} />
        <Route path="/:id" element={<DetailProduct />} />
        <Route path="/register" element={<RegisterDisplay />} />
        <Route path="/cart" element={<DisplayCart/>} />
        <Route path="/listpro/:tagId" element={<DisplayListPro/>} />
        <Route path="/listpro" element={<DisplayListPro/>} />
        <Route path="/payment" element={<Payment/>} />
        <Route path="/order" element={<Order/>} />
      </Routes>
    </main>
  )
}

export default Main;
