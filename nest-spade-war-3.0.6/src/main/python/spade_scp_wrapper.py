#!/usr/bin/env python

scp_server = '/usr/bin/scp'

if __name__ == '__main__' :

    from optparse import OptionParser, OptionGroup, IndentedHelpFormatter
    parser = OptionParser(usage='scp-wrapper [-d dir]',
                          version='%prog 1.0')
    parser.add_option('-d',
                      '--directory',
                      dest = 'DIRECTORY',
                      help = 'limit actions this directory or below',
                      default = None)
    def suppress_error(self, message):
                pass
    import new
    parser.error = new.instancemethod(suppress_error,
                                      parser,
                                      parser.__class__)
    import sys
    try:
        (options, args) = parser.parse_args()
    except:
        sys.stderr.write('account restricted: misconfigured destination\n')
        sys.exit(1)
   
    import os
    try:
        command = os.environ['SSH_ORIGINAL_COMMAND']
    except:
        sys.stderr.write('account restricted: only non-interactive access allowed\n')
        sys.exit(-1)

    tokens = command.split()
    if 'scp' != tokens[0]:
        sys.stderr.write('account restricted: only scp is allowed\n')
        sys.exit(-2)

    scpParser = OptionParser(usage='scp [-pv] -t file',
                             version='%prog')
    scpParser.disable_interspersed_args()
    scpParser.add_option('-p',
                         dest='PRESERVE',
                         action='store_true',
                         default = False)
    scpParser.add_option('-t',
                         dest='TRANSFER',
                         action='store_true',
                         default = False)
    scpParser.add_option('-v',
                         dest='VERBOSE',
                         action='store_true',
                         default = False)
    scpParser.error = new.instancemethod(suppress_error,
                                         scpParser,
                                         scpParser.__class__)
    try:
        (scpOptions, scpArgs) = scpParser.parse_args(tokens[1:])
    except:
        sys.stderr.write('account restricted: only limited delivery is allowed\n')
        sys.exit(-3)

    if not scpOptions.TRANSFER:
        sys.stderr.write('account restricted: only delivery is allowed\n')
        sys.exit(-3)

    if None != options.DIRECTORY:
        for arg in scpArgs:
            if not os.path.abspath(arg).startswith(options.DIRECTORY):
                sys.stderr.write('account restricted: only SPADE delivery is allowed\n')
                sys.exit(-4)

    tokens[0] = scp_server
    os.execve(scp_server, tokens, {})
