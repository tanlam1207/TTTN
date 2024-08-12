import React from 'react'
import CIcon from '@coreui/icons-react'
import {
  cilBell,
  cilCalculator,
  cilChartPie,
  cilCursor,
  cilDescription,
  cilDrop,
  cilNotes,
  cilPencil,
  cilPuzzle,
  cilSpeedometer,
  cilStar,
} from '@coreui/icons'
import { CNavGroup, CNavItem, CNavTitle } from '@coreui/react'

const _nav = [
  {
    component: CNavItem,
    name: 'Dashboard',
    to: '/dashboard',
    icon: <CIcon icon={cilSpeedometer} customClassName="nav-icon" />,
    badge: {
      color: 'info',
      text: 'NEW',
    },
  },

  {
    component: CNavGroup,
    name: 'Produtcs',
    icon: <CIcon icon={cilStar} customClassName="nav-icon" />,
    route: '/products',
    items: [
      {
        component: CNavItem,
        name: 'Trash Products',
        to: '/products/trash-products',
      },
      {
        component: CNavItem,
        name: 'All Products',
        to: '/products/allProducts',
      },
    ],
  },
  {
    component: CNavGroup,
    name: 'Brands',
    icon: <CIcon icon={cilStar} customClassName="nav-icon" />,
    route: '/brands',
    items: [
      {
        component: CNavItem,
        name: 'Trash Brands',
        to: '/brands/trash-brands',
      },
      {
        component: CNavItem,
        name: 'All Brands',
        to: '/brands/allBrands',
      },
    ],
  },
  {
    component: CNavGroup,
    name: 'Categories',
    icon: <CIcon icon={cilStar} customClassName="nav-icon" />,
    route: '/category',
    items: [
      {
        component: CNavItem,
        name: 'Trash Brands',
        to: '/category/trash-categories',
      },
      {
        component: CNavItem,
        name: 'All Categories',
        to: '/category/allCategories',
      },
    ],
  },
  {
    component: CNavGroup,
    name: 'Slidershow',
    icon: <CIcon icon={cilStar} customClassName="nav-icon" />,
    route: '/slidershow',
    items: [

      {
        component: CNavItem,
        name: 'All Slidershows',
        to: '/slidershow/allSlidershows',
      },
    ],
  },
]

export default _nav
