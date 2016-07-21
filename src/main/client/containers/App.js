import React, { Component, PropTypes } from 'react'
import { connect } from 'react-redux'
import { browserHistory } from 'react-router'
import { resetErrorMessage, createTrail } from '../actions'

import { MuiThemeProvider, getMuiTheme } from 'material-ui/styles'

import Contents from '../components/Contents'

import injectTapEventPlugin from 'react-tap-event-plugin'
injectTapEventPlugin()

class App extends Component {
  static propTypes = {
    errorMessage: PropTypes.string,
    resetErrorMessage: PropTypes.func.isRequired,
    inputValue: PropTypes.string.isRequired,
    children: PropTypes.node,
    createTrail: PropTypes.func.isRequired
  }

  constructor(props) {
    super(props)
    this.handleDismissClick = this.handleDismissClick.bind(this)
  }

  handleDismissClick(e) {
    this.props.resetErrorMessage()
    e.preventDefault()
  }

  handleUrlChange = (nextValue) => {
    browserHistory.push(`${process.env.CONTEXT_PATH}${nextValue}`)
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
    const { children } = this.props
    return (
      <MuiThemeProvider muiTheme={getMuiTheme()}>
        <Contents children={children}
          handleUrlChange={this.handleUrlChange}
          handleTest={this.props.createTrail.bind(this)} />
      </MuiThemeProvider>
    )
  }
}

function mapStateToProps(state, ownProps) {
  return {
    errorMessage: state.errorMessage,
    inputValue: ownProps.location.pathname.substring(1)
  }
}

export default connect(mapStateToProps, {
  resetErrorMessage,
  createTrail
})(App)
