import React, { Component, PropTypes } from 'react'
import {Toolbar, ToolbarGroup, ToolbarTitle} from 'material-ui/Toolbar'
import IconMenu from 'material-ui/IconMenu'
import IconButton from 'material-ui/IconButton'
import NavigationExpandMoreIcon from 'material-ui/svg-icons/navigation/expand-more'

export default class ErrorBox extends Component {
  static propTypes = {
    message: PropTypes.string
  }

  render() {
    if (!this.props.message || this.props.message === '') {
      return null
    }
    return (
      <Toolbar>
        <ToolbarGroup>
          <ToolbarTitle text={this.props.message} />
        </ToolbarGroup>
        <ToolbarGroup style={{display: 'flex', alignItems: 'center'}}>
          <IconMenu iconButtonElement={
            <IconButton touch={true}>
              <NavigationExpandMoreIcon />
            </IconButton>
          }>
          </IconMenu>
        </ToolbarGroup>
      </Toolbar>
    )
  }
}
