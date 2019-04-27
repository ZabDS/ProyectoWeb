const {
  Button,
  AppBar,
  Toolbar,
  Paper,
  Card,
  CardActionArea,
  CardActions,
  CardContent,
  CardMedia,
  IconButton,
  Snackbar,
  CloseIcon,
  MenuIcon,
  TextField,
  Grid,
  Divider,
  List,
  ListItem,
  ListItemText,
  colors,
  createMuiTheme,
  CssBaseline,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Icon,
  MuiThemeProvider,
  Typography,
  withStyles,
} = window['material-ui'];

const theme = createMuiTheme({
  palette: {
    type:'dark',
    primary: {
      light: colors.blue[300],
      main: colors.blue[500],
      dark: colors.blue[700],
    },
    secondary: {
      light: colors.blue[300],
      main: colors.blue[500],
      dark: colors.blue[700],
    },
  },
  typography: {
    useNextVariants: true,
  },
});

const styles = theme => ({
  root: {
      position: 'absolute',
        left: '50%',
        top: '50%',
        transform: 'translate(-50%, -50%)',
  },
  List:{
    width: '100%',
    maxWidth: 360,
    backgroundColor: theme.palette.background.paper,
  },
  card: {
    maxWidth: 500,
    padding: theme.spacing.unit * 2,
    textAlign: 'center',
  },
  paper: {
    padding: theme.spacing.unit * 2,
    maxWidth: 650,
    textAlign: 'center',
    color: 'white',
  },
  media: {
    height: 300,
  },
  grow: {
    flexGrow: 1,
  },
  container: {
    display: 'flex',
    flexWrap: 'wrap',
  },
  textField: {
    marginLeft: theme.spacing.unit,
    marginRight: theme.spacing.unit,
    width: 200,
  },
  close: {
    padding: theme.spacing.unit / 2,
    backgroundColor: colors.green[600],    
    textAlign: 'center',
  },
});
var QuestName;
var QuestText;
var FormText;
var AnswerTol;
var Points;

class Index extends React.Component {
   sleep = time => {
        return new Promise((resolve) => setTimeout(resolve, time));
    };
    
  state = {
    open: false,
  };
    
 GetQuestName = event => {
     QuestName=event.target.value;
  };
  GetQuestText = event => {
     QuestText=event.target.value;
  };
  GetFormText = event => {
     FormText=event.target.value;
  };
  GetAnswerTol = event => {
     AnswerTol=event.target.value;
  };
  GetPoints = event => {
     Points=event.target.value;
  };
  handleClick = event => {
        this.setState({ open: true });
        this.sleep(500).then(() => {
            window.location.href="AltaPregunta?Nombre="+QuestName+"&Texto="+QuestText+"&Formula="+FormText+"&Puntuacion="+Points;
        });
  };
    render() {
    const { classes } = this.props;
    return (
      <MuiThemeProvider theme={theme}>
        <div className={classes.root}>
          <CssBaseline />
        <Grid container spacing={0} justfy="center" alignItems="center">
        <Grid item xs={0}>
        <List className={classes.List}>
         <Card className={classes.card}>
           <CardActionArea>
         <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            Crear Pregunta
          </Typography>
          <Typography>
           <form className={classes.container} noValidate autoComplete="off">
             <TextField
                id="QuestName"
                className={classes.textField}
                margin="normal"
                label="Nombre de la pregunta:"
                InputLabelProps={{ shrink: true,}}
                onChange={this.GetQuestName}
                />

                <TextField
                id="QuestText"
                className={classes.textField}
                margin="normal"
                label="Enunciado de tu pregunta:"
                multiline
                rowsMax="10"
                helperText="Usa {} para definir las variables en tu pregunta"
                InputLabelProps={{ shrink: true,}}
                onChange={this.GetQuestText}
                />

                <TextField
                   id="Points"
                   className={classes.textField}
                   margin="normal"
                   label="Puntuación de la pregunta:"
                   InputLabelProps={{ shrink: true,}}
                   onChange={this.GetPoints}
                   />

                <ListItem>
                    <li>
                     <Divider variant="inset" />
                    </li>
                    <Typography gutterBottom variant="h7" component="h7">
                Respuesta
                </Typography>
                </ListItem>
                
                <TextField
                id="FormText"
                className={classes.textField}
                margin="normal"
                label="Fórmula para resolver la pregunta:"
                multiline
                rowsMax="4"
                helperText="Usa {} para usar las variables definidas en tu pregunta"
                InputLabelProps={{ shrink: true,}}
                onChange={this.GetFormText}
                />
                <Snackbar
                anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
                }}
                open={this.state.open}
                autoHideDuration={600}
                onClose={this.handleClose}
                ContentProps={{
                'aria-describedby': 'message-id',
                classes: {
                        root: classes.close                        
                        }
                }}
                message={<span id="message-id">Pregunta Creada Correctamente</span>}
                />
                
           </form>
          </Typography>
        </CardContent>
           </CardActionArea>
           <CardActions>
        <Button color="primary" onClick={this.handleClick}>
          Crear!
        </Button>
      </CardActions>
         </Card>
         </List>
         </Grid>
         </Grid>
         </div>
      </MuiThemeProvider>
    );
  }
}

const App = withStyles(styles)(Index);

ReactDOM.render(<App />, document.getElementById('CrearPregunta'));
