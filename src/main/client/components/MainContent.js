import React, { Component, PropTypes } from 'react'
import { RaisedButton } from 'material-ui'

export default class MainContent extends Component {

  static propTypes = {
    handleUrlChange: PropTypes.func.isRequired,
    children: PropTypes.node,
    handleTest: PropTypes.func.isRequired
  }

  render() {
    return (
      <div>
        <div style={{margin: 10, display: 'flex', justifyContent: 'flex-start'}}>
          <RaisedButton label="log list" onMouseUp={this.props.handleUrlChange.bind(null, '/timber/list')} />
          <RaisedButton label="test" onMouseUp={this.props.handleTest} style={{marginLeft: '10px'}} />
        </div>
        {this.props.children}
      </div>
    )
  }
}
