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
} = MaterialUI;

const theme = createMuiTheme({
    palette: {
        type: 'dark',
        primary: {
            light: colors.blue[300],
            main: colors.blue[500],
            dark: colors.blue[700],
        },
        secondary: {
            light: colors.green[300],
            main: colors.green[500],
            dark: colors.green[700],
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
        List: {
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
    });
var QuestName;
var QuestText;
var FormText;
var AnswerTol;
var Points;

class Index extends React.Component {
    constructor(props) {
        super(props);
    }

    handleChange(event) {
        this.setState({defaultValue: event.target.defaultValue});
    }
    render() {
        const {classes} = this.props;
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
                                            <form className={classes.container} noValidate autoComplete="off" method="POST" action="ModificaPreguntas">
                                                <input type="hidden" name="tipoDePregunta" defaultValue="calculada"/>
                                                <input type="hidden" name="nombreOriginal" defaultValue={nombreDePregunta}/>
                                                <TextField
                                                   id="Nombre"
                                                   name="Nombre"
                                                    defaultValue={nombreDePregunta}

                                                   className={classes.textField}
                                                   margin="normal"
                                                   label="Nombre de la pregunta:"
                                                   InputLabelProps={{ shrink: true,}}
                                                   onChange={this.handleChange}
                                                   />
                                                <TextField
                                                    id="Texto"
                                                    name="Texto"
                                                   defaultValue={enunciado}                                                   
                                                    className={classes.textField}
                                                    margin="normal"
                                                    label="Enunciado de tu pregunta:"
                                                    multiline
                                                    rowsMax="10"
                                                    helperText="Usa {} para definir las variables en tu pregunta"
                                                    InputLabelProps={{shrink: true, }}
                                                    onChange={this.handleChange}
                                                    />
                
                                                <TextField
                                                    id="Puntuacion"
                                                    name="Puntuacion"
                                                    defaultValue={puntuacion}
                                                    className={classes.textField}
                                                    margin="normal"
                                                    label="Puntuación de la pregunta:"
                                                    InputLabelProps={{shrink: true, }}
                                                    onChange={this.handleChange}
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
                                                    id="Formula"
                                                    name="Formula"
                                                    defaultValue={formula}
                                                    className={classes.textField}
                                                    margin="normal"
                                                    label="Fórmula para resolver la pregunta:"
                                                    multiline
                                                    rowsMax="4"
                                                    helperText="Usa {} para usar las variables definidas en tu pregunta"
                                                    InputLabelProps={{shrink: true, }}
                                                    onChange={this.handleChange}
                                                    />
                                                <input type="submit" value="Modificar!"/>
                                            </form>
                                        </Typography>
                                    </CardContent>
                                </CardActionArea>
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

ReactDOM.render(<App />, document.getElementById('ModificarPregunta'));

