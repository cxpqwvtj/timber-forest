import React, { Component, PropTypes } from 'react'
import { RaisedButton } from 'material-ui'
import { loadLogs } from '../actions'

import { connect } from 'react-redux'

export default class MainContent extends Component {

  static propTypes = {
    errorMessage: PropTypes.string,
    children: PropTypes.node,
    loadLogs: PropTypes.func.isRequired
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

  fetchData = () => {
    this.props.loadLogs({endpoint: '/api/filelist'})
  }

  render() {
    return (
      <div>
          <div style={{margin: 10}}>
            <RaisedButton label="テスト" onMouseUp={this.buttonClick} />
          </div>
          <div style={{margin: 10}}>
            <RaisedButton label="ボタン2" onMouseUp={this.fetchData} />
          </div>
          {this.props.children}
          {this.renderErrorMessage()}
      </div>
    )
  }
}

function mapStateToProps(state) {
  return state
}

export default connect(mapStateToProps, {
  loadLogs
})(MainContent)
