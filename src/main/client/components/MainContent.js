import React, { Component, PropTypes } from 'react'
import { RaisedButton } from 'material-ui'

export default class MainContent extends Component {

  static propTypes = {
    handleUrlChange: PropTypes.func.isRequired,
    children: PropTypes.node,
    errorMessage: PropTypes.string
  }

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
            <RaisedButton label="log list" onMouseUp={this.props.handleUrlChange.bind(null, '/file/list')} />
          </div>
          {this.props.children}
          {this.renderErrorMessage()}
      </div>
    )
  }
}
