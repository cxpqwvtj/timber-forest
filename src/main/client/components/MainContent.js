import React, { Component, PropTypes } from 'react'
import { RaisedButton } from 'material-ui'

export default class MainContent extends Component {

  renderErrorMessage() {
    const { errorMessage } = this.props
    if (!errorMessage) {
      return null
    }

    return (
      <p style={{ backgroundColor: '#e99', padding: 10 }}>
        <b>{errorMessage}</b>
        {' '}
        (<a href="#"
            onClick={this.handleDismissClick}>
          Dismiss
        </a>)
      </p>
    )
  }

  render() {
    return (
      <div>
          <div style={{margin: 10}}>
            <RaisedButton label="テスト" onMouseUp={this.buttonClick} />
          </div>
          <div style={{margin: 10}}>
            <RaisedButton label="ボタン2" />
          </div>
          {this.props.children}
          {this.renderErrorMessage()}
      </div>
    )
  }
}

MainContent.propTypes = {
  errorMessage: PropTypes.string,
  children: PropTypes.node
}
