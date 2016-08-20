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
        <div style={{height: '0px', display: 'flex', justifyContent: 'center', visibility: 'hidden'}}>
          <div style={{width: '300px', height: '300px', borderRadius: '300px', backgroundColor: 'red', display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
            <div style={{fontSize: '100px', color: 'white'}}>400</div>
          </div>
          <div style={{width: '300px', height: '300px', borderRadius: '300px', backgroundColor: 'blue', display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
            <div style={{fontSize: '100px', color: 'white'}}>500</div>
          </div>
        </div>
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
