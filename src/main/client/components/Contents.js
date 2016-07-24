import React, { Component, PropTypes } from 'react'

import Header from './Header'
import ErrorBox from './ErrorBox'
import MainContent from './MainContent'
import Footer from './Footer'

export default class Contents extends Component {
  static propTypes = {
    handleUrlChange: PropTypes.func.isRequired,
    children: PropTypes.node,
    errorMessage: PropTypes.string,
    handleTest: PropTypes.func.isRequired
  }
  render() {
    return (
      <div>
        <Header />
        <ErrorBox message={this.props.errorMessage} />
        <MainContent
          handleUrlChange={this.props.handleUrlChange}
          children={this.props.children}
          handleTest={this.props.handleTest}
          errorMessage={this.props.errorMessage} />
        <Footer />
      </div>
    )
  }
}
