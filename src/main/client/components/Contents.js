import React, { Component, PropTypes } from 'react'

import Header from './Header'
import MainContent from './MainContent'
import Footer from './Footer'

export default class Contents extends Component {
  static propTypes = {
    handleUrlChange: PropTypes.func.isRequired,
    children: PropTypes.node
  }
  render() {
    return (
      <div>
        <Header />
        <MainContent handleUrlChange={this.props.handleUrlChange} children={this.props.children} />
        <Footer />
      </div>
    )
  }
}
